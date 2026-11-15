#!/usr/bin/env bash
# Renomeia o template (modid, nome, pacote e classe principal) para o seu mod.
# Uso: ./rename.sh <modid> "<Nome do Mod>" <pacote> [ClassePrincipal] [Autor]
# Ex.: ./rename.sh meumod "Meu Mod" dev.gabriel.meumod MeuMod Gabriel
set -euo pipefail
cd "$(dirname "$0")"

MOD_ID="${1:?modid}"; MOD_NAME="${2:?nome}"; PACKAGE="${3:?pacote}"
CLASS_NAME="${4:-$(echo "$MOD_NAME" | sed -E 's/[^A-Za-z0-9]+/ /g' | awk '{for(i=1;i<=NF;i++) printf "%s", toupper(substr($i,1,1)) substr($i,2)}')}"
AUTHOR="${5:-YourName}"

[[ "$MOD_ID" =~ ^[a-z][a-z0-9_]{1,63}$ ]] || { echo "modid invalido"; exit 1; }
[[ "$PACKAGE" =~ ^[a-z][a-z0-9_]*(\.[a-z][a-z0-9_]*)+$ ]] || { echo "pacote invalido"; exit 1; }
[[ "$CLASS_NAME" =~ ^[A-Z][A-Za-z0-9]*$ ]] || { echo "classe invalida: $CLASS_NAME"; exit 1; }
[[ -d src/main/java/com/example/examplemod ]] || { echo "template ja renomeado?"; exit 1; }

esc() { printf '%s' "$1" | sed -e 's/[\/&|]/\\&/g'; }
find src gradle.properties -type f \( -name '*.java' -o -name '*.json' -o -name '*.toml' -o -name '*.properties' -o -name '*.gradle' -o -name '*.mcmeta' -o -name '*.cfg' \) -print0 |
  xargs -0 sed -i \
    -e "s|com\.example\.examplemod|$(esc "$PACKAGE")|g" \
    -e "s|ExampleMod|$(esc "$CLASS_NAME")|g" \
    -e "s|Example Mod|$(esc "$MOD_NAME")|g" \
    -e "s|examplemod|$(esc "$MOD_ID")|g" \
    -e "s|YourName|$(esc "$AUTHOR")|g"

for f in src/main/java/com/example/examplemod/ExampleMod*.java; do mv "$f" "${f/ExampleMod/$CLASS_NAME}"; done
mv src/main/java/com/example/examplemod src/main/java/__pkg_tmp__
rmdir src/main/java/com/example 2>/dev/null || true; rmdir src/main/java/com 2>/dev/null || true
DEST="src/main/java/${PACKAGE//.//}"; mkdir -p "$(dirname "$DEST")"; mv src/main/java/__pkg_tmp__ "$DEST"
mv src/main/resources/assets/examplemod "src/main/resources/assets/$MOD_ID"
mv src/main/resources/examplemod.mixins.json "src/main/resources/$MOD_ID.mixins.json"
for d in src/generated/resources/*/examplemod; do [[ -d "$d" ]] && mv "$d" "$(dirname "$d")/$MOD_ID"; done 2>/dev/null || true

echo "Pronto! modid=$MOD_ID | nome=$MOD_NAME | pacote=$PACKAGE | classe=$CLASS_NAME"
