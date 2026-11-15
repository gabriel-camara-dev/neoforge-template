<#
.SYNOPSIS
  Renomeia o template (modid, nome, pacote e classe principal) para o seu mod.

.EXAMPLE
  .\rename.ps1 -ModId meumod -ModName "Meu Mod" -Package dev.gabriel.meumod -Author "Gabriel"

  Se o PowerShell bloquear o script, rode:
  powershell -ExecutionPolicy Bypass -File .\rename.ps1 -ModId meumod -ModName "Meu Mod" -Package dev.gabriel.meumod
#>
param(
    [Parameter(Mandatory = $true)][string]$ModId,
    [Parameter(Mandatory = $true)][string]$ModName,
    [Parameter(Mandatory = $true)][string]$Package,
    [string]$ClassName,
    [string]$Author = "YourName"
)

$ErrorActionPreference = "Stop"
Set-Location -Path $PSScriptRoot

$OldModId   = "examplemod"
$OldName    = "Example Mod"
$OldPackage = "com.example.examplemod"
$OldClass   = "ExampleMod"

if ($ModId -cnotmatch '^[a-z][a-z0-9_]{1,63}$') { throw "ModId invalido: use [a-z][a-z0-9_]{1,63} (ex.: meumod)" }
if ($Package -cnotmatch '^[a-z][a-z0-9_]*(\.[a-z][a-z0-9_]*)+$') { throw "Pacote invalido (ex.: dev.seunome.meumod)" }
if (-not $ClassName) {
    $ClassName = (($ModName -split '[^A-Za-z0-9]+') | Where-Object { $_ } | ForEach-Object { $_.Substring(0,1).ToUpper() + $_.Substring(1) }) -join ''
}
if ($ClassName -cnotmatch '^[A-Z][A-Za-z0-9]*$') { throw "ClassName invalido: '$ClassName' (passe -ClassName MeuMod)" }
if (-not (Test-Path "src/main/java/com/example/examplemod")) { throw "O template parece ja ter sido renomeado." }

$utf8 = New-Object System.Text.UTF8Encoding $false
$exts = @('.java', '.json', '.toml', '.properties', '.gradle', '.mcmeta', '.cfg')

# 1) Substitui o texto dentro dos arquivos
$files = @(Get-ChildItem -Path src -Recurse -File) + @(Get-Item gradle.properties)
foreach ($f in $files) {
    if ($exts -notcontains $f.Extension) { continue }
    $c = [System.IO.File]::ReadAllText($f.FullName)
    $n = $c.Replace($OldPackage, $Package).Replace($OldClass, $ClassName).Replace($OldName, $ModName).Replace($OldModId, $ModId).Replace("YourName", $Author)
    if ($n -cne $c) { [System.IO.File]::WriteAllText($f.FullName, $n, $utf8) }
}

# 2) Renomeia arquivos de classe (ExampleMod*.java)
Get-ChildItem -Path "src/main/java/com/example/examplemod" -File -Filter "$OldClass*.java" | ForEach-Object {
    Rename-Item -Path $_.FullName -NewName ($_.Name.Replace($OldClass, $ClassName))
}

# 3) Move o pacote
$tmp = "src/main/java/__pkg_tmp__"
Move-Item "src/main/java/com/example/examplemod" $tmp
if (-not (Get-ChildItem "src/main/java/com/example" -Force)) { Remove-Item "src/main/java/com/example" }
if (-not (Get-ChildItem "src/main/java/com" -Force)) { Remove-Item "src/main/java/com" }
$dest = Join-Path "src/main/java" ($Package -replace '\.', '/')
$parent = Split-Path $dest -Parent
New-Item -ItemType Directory -Force -Path $parent | Out-Null
Move-Item $tmp $dest

# 4) Renomeia assets e mixins
Rename-Item "src/main/resources/assets/$OldModId" $ModId
Rename-Item "src/main/resources/$OldModId.mixins.json" "$ModId.mixins.json"
if (Test-Path "src/generated/resources") {
    Get-ChildItem "src/generated/resources" -Directory | ForEach-Object {
        $p = Join-Path $_.FullName $OldModId
        if (Test-Path $p) { Rename-Item $p $ModId }
    }
}

Write-Host ""
Write-Host "Pronto! modid=$ModId | nome=$ModName | pacote=$Package | classe=$ClassName" -ForegroundColor Green
Write-Host "Agora: recarregue o projeto Gradle no IntelliJ e rode a configuracao 'Data' antes do 'Client'."
