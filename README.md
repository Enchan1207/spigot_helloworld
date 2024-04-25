# Hello, spigotmc!

## Overview

Spigotプラグイン開発のサンプル

## Instruction

### Create maven project

まずはmavenプロジェクトを作成する  
アーキタイプ *[maven-archetype-quickstart](https://maven.apache.org/archetypes/maven-archetype-quickstart/)* は、mavenのサンプルプロジェクトを生成するためのもの

```sh
mvn archetype:generate -DarchetypeGroupId=org.apache.maven.archetypes -DarchetypeArtifactId=maven-archetype-quickstart -DarchetypeVersion=1.4
```

`groupId` (プラグインのidentifier) を聞かれるので、入力する ここでは `me.enchan.hello_world` とした

```
Define value for property 'groupId':
```

`artifactId` を聞かれるので、入力する ここでは `HelloWorld` とした

```
Define value for property 'artifactId':
```

バージョンを聞かれる 問題がなければデフォルトのままでよいと思う

```
Define value for property 'version' 1.0-SNAPSHOT:
```

パッケージ名も同様

```
Define value for property 'package' me.enchan.hello_world:
```

ここまで進むと生成されるプロジェクトの情報が表示され、確認を求められる 問題がなければ進む

```
Confirm properties configuration:
groupId: me.enchan.hello_world
artifactId: HelloWorld
version: 1.0-SNAPSHOT
package: me.enchan.hello_world
```

### Remove unused source

今回はテストコードは使用しないので、JUnitの依存関係を削除する

```diff
diff --git a/pom.xml b/pom.xml
index 69ae80d..b52cbf7 100644
--- a/pom.xml
+++ b/pom.xml
@@ -19,12 +18,6 @@
   </properties>
 
   <dependencies>
-    <dependency>
-      <groupId>junit</groupId>
-      <artifactId>junit</artifactId>
-      <version>4.11</version>
-      <scope>test</scope>
-    </dependency>
   </dependencies>
 
   <build>
```

### Add dependency

mavenリポジトリにSpigotをインストールし、[Spigotのチュートリアル](https://www.spigotmc.org/wiki/spigot-maven/)を参考にしつつプロジェクトに依存関係を追加する

```xml
<repositories>
    <repository>
        <id>spigot-repo</id>
        <url>https://hub.spigotmc.org/nexus/content/repositories/snapshots/</url>
    </repository>
</repositories>

<dependencies>
    <dependency>
        <groupId>org.spigotmc</groupId>
        <artifactId>spigot-api</artifactId>
        <version>1.18.2-R0.1-SNAPSHOT</version>
        <scope>provided</scope>
    </dependency>
</dependencies>
```

### Set up plugin file

`src/main/resources` に `plugin.yml` を追加する

```yml
main: me.enchan.hello_world.App
name: hello_world
version: "0.1.0"
api: 1.16.2
api-version: "1.17"
```

### Set up plugin base

プラグインの基礎を書いていく  
`org.bukkit.plugin.java.JavaPlugin` をimportし、extendする

```java
package me.enchan.hello_world;

import org.bukkit.plugin.java.JavaPlugin;

public class App extends JavaPlugin {

    @Override
    public void onEnable() {
        this.getLogger().info("Plugin enabled");
    }

    @Override
    public void onDisable() {
        this.getLogger().info("Plugin disabled");
    }

}
```

### Build plugin

ここまできたら、プラグインをビルドしてみる

```
mvn clean install
```

```
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  1.923 s
[INFO] Finished at: 2024-04-25T22:47:20+09:00
[INFO] ------------------------------------------------------------------------
```

成功すると、`target` ディレクトリ配下にプラグインjarが生成される

```
% tree -L 1 target
target
├── HelloWorld-1.0-SNAPSHOT.jar   <--- これ
├── classes
├── generated-sources
├── maven-archiver
└── maven-status
```

このjarファイルをサーバの `spigot.jar` と同じディレクトリにある `plugins` 内に投入し、
サーバを起動するか `/reload` を実行することでプラグインが動き出す

## License

This repository is published under [MIT License](LICENSE).
