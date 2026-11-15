# NeoForge Mod Template (Minecraft 26.3)

Template de mod para **NeoForge / Minecraft 26.3 (Java 25)**, organizado em camadas no mesmo estilo dos
tutoriais do Kaupenjoe ([NeoForge-Tutorial-26.X](https://github.com/Tutorials-By-Kaupenjoe/NeoForge-Tutorial-26.X)):
um pacote por "camada" e uma classe `ModXxx` com o `DeferredRegister` de cada uma.

Vem com **um exemplo mínimo de cada camada**, pronto para copiar e colar quando você for criar conteúdo novo.

## Começando um mod novo

1. No GitHub, clique em **Use this template → Create a new repository** (ou clone/baixe o zip).
2. Rode o script de renomear na raiz do projeto:

   **Windows (PowerShell):**
   ```powershell
   powershell -ExecutionPolicy Bypass -File .\rename.ps1 -ModId meumod -ModName "Meu Mod" -Package dev.seunome.meumod -Author "Seu Nome"
   ```
   **Linux/macOS:**
   ```bash
   ./rename.sh meumod "Meu Mod" dev.seunome.meumod MeuMod "Seu Nome"
   ```
   Ele troca `examplemod`, `Example Mod`, `com.example.examplemod` e `ExampleMod` em todo lugar,
   move as pastas do pacote e renomeia `assets/examplemod` e o `mixins.json`.
   `-ClassName` é opcional (por padrão vira o nome do mod em PascalCase).
3. Apague `rename.ps1` / `rename.sh` se quiser.

## Abrindo no IntelliJ IDEA

1. **File → Open** e escolha a pasta do projeto (o `build.gradle`). Confirme "Trust Project".
2. Em **Settings → Build, Execution, Deployment → Build Tools → Gradle**, deixe *Gradle JVM* em um **JDK 25**
   (se não tiver, o próprio IntelliJ baixa: *Download JDK → 25*).
3. Espere o sync do Gradle terminar (a primeira vez demora: ele baixa e descompila o Minecraft).
   O ModDevGradle cria sozinho as run configurations **Client**, **Server**, **Data** e **GameTestServer**.
   Se não aparecerem, abra a aba Gradle e clique em *Reload All Gradle Projects*.
4. Rode **Data** uma vez (gera models, blockstates, loot tables, tags e receitas em `src/generated/resources`).
5. Rode **Client** para abrir o jogo com o mod.

Pela linha de comando: `gradlew runData`, `gradlew runClient`, `gradlew build` (o .jar sai em `build/libs`).

## Estrutura

```
src/main/java/com/example/examplemod/
├── ExampleMod.java            # classe principal: liga todos os registries ao mod event bus
├── ExampleModClient.java      # entrada só-cliente: renderers, telas, teclas, config screen
├── ExampleModDataGen.java     # registra os providers de datagen
├── Config.java                # ModConfigSpec (config/examplemod-common.toml)
├── item/
│   ├── ModItems.java          # DeferredRegister.Items
│   └── custom/ExampleToolItem # item com lógica própria (useOn, tooltip, data component)
├── block/
│   ├── ModBlocks.java         # DeferredRegister.Blocks + registro automático do BlockItem
│   ├── custom/ExampleCounterBlock
│   └── entity/
│       ├── ModBlockEntities.java
│       └── custom/ExampleCounterBlockEntity  # salva dados com ValueOutput/ValueInput
├── creativemodetab/ModCreativeModeTabs.java
├── component/ModDataComponents.java          # dados extras em ItemStack
├── tags/ModTags.java                         # TagKeys do mod
├── effect/ModEffects.java + ExampleEffect    # efeito de poção
├── sound/ModSounds.java                      # (vazio, com exemplo comentado)
├── entity/ModEntities.java                   # (vazio, com o passo a passo de uma entidade)
├── event/ModEvents.java                      # eventos comuns + registro de pacotes
├── networking/
│   ├── packet/ExamplePacketC2S.java          # pacote cliente -> servidor (record + StreamCodec)
│   └── ServerPayloadHandler.java
├── keymapping/ModKeyMappings.java            # tecla K de exemplo
├── mixin/                                    # coloque mixins aqui e liste no examplemod.mixins.json
└── datagen/
    ├── ModModelProvider.java                 # models/blockstates
    ├── ModBlockTagsProvider.java / ModItemTagsProvider.java
    ├── ModBlockLootTableProvider.java
    ├── ModRecipeProvider.java
    └── ModDatapackProvider.java              # liga loot tables e receitas (e worldgen, se usar)

src/main/resources/assets/examplemod/
├── lang/en_us.json, pt_br.json
└── textures/{item,block,mob_effect}/
```

## Receita para adicionar coisas

**Item novo:** `ModItems` → textura em `textures/item/` → `ModModelProvider` → lang → (aba criativa).

**Bloco novo:** `ModBlocks.registerBlock(...)` → textura em `textures/block/` → `ModModelProvider` →
`ModBlockLootTableProvider` → ferramenta em `ModBlockTagsProvider` → lang → rodar **Data**.

**Entidade nova:** veja o comentário em `entity/ModEntities.java`.

Coisas mais avançadas (worldgen, árvores, menus/GUIs, receitas customizadas, villagers, dimensões, JEI)
seguem o mesmo padrão: um pacote novo com uma classe `ModXxx` + `register(IEventBus)` chamada em `ExampleMod`.
O repositório do Kaupenjoe tem exemplos completos de cada uma.

## Atualizando versões

As versões ficam em `gradle.properties` (`minecraft_version`, `neo_version`).
Veja a última versão do NeoForge em https://projects.neoforged.net/neoforged/neoforge.

## Licença

Os arquivos de build vêm do [NeoForged MDK](https://github.com/NeoForged/MDK) (ver `TEMPLATE_LICENSE.txt`).
Troque `mod_license` no `gradle.properties` pela licença do seu mod.
