Name of 'assembly' function causing difference in dependent tasks invalidation
===

## How to reproduce issue

Optional: use nix to get reproducible dependencies:

``` sh
nix develop
```

Install npm dependencies.

``` sh
./mill shared.npmInstall
```

Run the command twice and verify that the second time it runs fast and rebuilds nothing

``` bash
time (./mill app.persistentAssembly && cat out/mill-invalidation-tree.json)
```

The second time should look like this: 

```
[162/162] ============================== app.persistentAssembly ==============================
{}
real    0m0.241s
user    0m0.173s
sys     0m0.057s
```

Now change the test log line in app.assembly in build.mill and run the
command again. When the task is called assembly, many things are rebuilt, including 
scalablytyped:

```
[162/162] ============================== app.persistentAssembly ============================== 23s
{
  "shared.scalablyTyped.zincAuxiliaryClassFileExtensions": {},
  "shared.scalablyTyped.scalablyTypedWantedLibs": {},
  "shared.zincAuxiliaryClassFileExtensions.super.scalalib.ScalaModule": {},
  "shared.checkGradleModules": {},
  "shared.scalablyTyped.checkGradleModules": {},
  "shared.scalacPluginMvnDeps.super.scalalib.ScalaModule": {},
  "shared.bomMvnDeps": {},
  "shared.scalablyTyped.scalaLibraryMvnDeps.super.scalalib.ScalaModule": {
    "shared.scalablyTyped.scalaLibraryMvnDeps": {
      "shared.scalablyTyped.mandatoryMvnDeps.super.scalalib.ScalaModule": {
        "shared.scalablyTyped.mandatoryMvnDeps": {}
      },
      "shared.scalablyTyped.scalaCompilerClasspath": {}
    }
  },
  "shared.scalablyTyped.scalablyTypedOutputPackage": {},
  "shared.allSources": {},
  "shared.scalablyTyped.mvnDeps.super.mill.javalib.JavaModule": {},
  "shared.scalablyTyped.compileClasspath": {},
  "shared.localClasspath": {},
  "com.github.lolgab.mill.scalablytyped.ScalablyTypedWorkerApi.scalablyTypedWorker": {},
  "shared.scalaJSJsEnvMvnDeps": {
    "shared.scalaJSLinkerClasspath": {}
  },
  "shared.scalablyTyped.scalablytypedWorkerClasspath": {},
  "shared.allScalacOptions": {},
  "shared.artifactTypes": {},
  "shared.scalablyTyped.scalacOptions": {},
  "shared.scalaJSSourceMap": {},
  "shared.localRunClasspath.super.RunModule": {},
  "shared.depManagement": {},
  "shared.mandatoryScalacOptions": {},
  "shared.localRunClasspath": {},
  "shared.upstreamCompileOutput": {},
  "shared.scalaOrganization": {},
  "shared.scalablyTyped.allSourceFiles": {},
  "shared.scalablyTyped.repositories": {},
  "shared.scalaJSVersion": {},
  "shared.scalablyTyped.crossFullScalaJSVersion": {},
  "shared.scalablyTyped.compileMvnDeps": {},
  "shared.scalaJSWorkerVersion": {},
  "shared.scalablyTyped.scalacPluginMvnDeps": {},
  "shared.scalablyTyped.scalablyTypedUseScalaJsDomTypes": {},
  "shared.scalaJSBinaryVersion": {},
  "shared.enablePluginScalacOptions": {},
  "shared.crossFullScalaJSVersion": {},
  "shared.scalablyTyped.zincAuxiliaryClassFileExtensions.super.scalalib.ScalaModule": {},
  "shared.scalaLibraryMvnDeps.super.scalalib.ScalaModule": {
    "shared.scalaLibraryMvnDeps": {
      "shared.scalaCompilerClasspath": {},
      "shared.mandatoryMvnDeps.super.scalalib.ScalaModule": {
        "shared.mandatoryMvnDeps": {}
      }
    }
  },
  "shared.scalablyTyped.jvmId": {},
  "shared.scalablyTyped.scalablyTypedBasePath": {},
  "mill.javalib.JvmWorkerModule.internalWorker": {},
  "shared.scalablyTyped.mandatoryMvnDeps.super.javalib.JavaModule": {},
  "shared.scalablyTyped.transitiveCoursierProjects": {},
  "shared.finalMainClassOpt": {},
  "shared.scalablyTyped.mandatoryScalacOptions": {},
  "shared.scalablyTyped.javaHome": {},
  "shared.runClasspath.super.RunModule": {},
  "mill.javalib.classgraph.ClassgraphWorkerModule.classgraphWorkerClassloader": {},
  "app.assemblyFe": {},
  "shared.scalablyTyped.scalablyTypedImportTask": {
    "shared.scalablyTyped.generatedSources": {}
  },
  "shared.scalablyTyped.upstreamCompileOutput": {},
  "shared.mandatoryMvnDeps.super.javalib.JavaModule": {},
  "shared.platformSuffix": {},
  "shared.scalablyTyped.artifactTypes": {},
  "shared.scalaJSOutputPatterns": {
    "shared.fastLinkJS": {
      "app.assembly": {}
    }
  },
  "shared.scalablyTyped.scalaVersion": {},
  "shared.javacOptions": {},
  "shared.scalablyTyped.compile": {},
  "shared.javaHome": {},
  "shared.scalaJSWorkerClasspath": {},
  "shared.zincAuxiliaryClassFileExtensions": {},
  "shared.compile": {},
  "shared.mvnDeps": {},
  "shared.scalablyTyped.unmanagedClasspath": {},
  "shared.transitiveLocalClasspath": {},
  "shared.scalablyTyped.localClasspath": {},
  "shared.unmanagedClasspath": {},
  "shared.jvmId": {},
  "shared.scalablyTyped.allSources": {},
  "shared.repositories": {},
  "shared.moduleKind": {},
  "shared.scalablyTyped.bomMvnDeps": {},
  "shared.scalablyTyped.scalablyTypedIncludeDev": {},
  "mill.javalib.classgraph.ClassgraphWorkerModule.classgraphWorker": {},
  "shared.scalablyTyped.scalaJSVersion": {},
  "shared.scalablyTyped.platformSuffix": {},
  "shared.moduleSplitStyle": {},
  "shared.scalablyTyped.artifactScalaJSVersion": {},
  "app.persistentAssembly": {},
  "shared.scalablyTyped.scalacPluginClasspath": {},
  "shared.scalablyTyped.zincAuxiliaryClassFileExtensions.super.javalib.JavaModule": {},
  "shared.scalablyTyped.scalablyTypedIncludePeer": {},
  "shared.zincIncrementalCompilation": {},
  "shared.scalablyTyped.localRunClasspath": {},
  "shared.scalablyTyped.depManagement": {},
  "shared.artifactScalaJSVersion": {},
  "shared.scalablyTyped.mandatoryScalacOptions.super.scalalib.ScalaModule": {},
  "shared.mandatoryScalacOptions.super.scalalib.ScalaModule": {},
  "shared.scalaJSExperimentalUseWebAssembly": {},
  "shared.scalaJSMinify": {},
  "shared.runClasspath": {},
  "shared.generatedSources": {},
  "shared.scalablyTyped.generatedSources.super.mill.javalib.JavaModule": {},
  "shared.scalablyTyped.mandatoryJavacOptions": {},
  "shared.allSourceFiles": {},
  "shared.scalablyTyped.scalablyTypedIgnoredLibs": {},
  "shared.scalablyTyped.allScalacOptions": {},
  "shared.runMvnDeps": {},
  "shared.mandatoryJavacOptions": {},
  "shared.zincAuxiliaryClassFileExtensions.super.javalib.JavaModule": {},
  "shared.scalablyTyped.scalacPluginMvnDeps.super.scalalib.ScalaModule": {},
  "shared.esFeatures": {},
  "shared.scalablyTyped.localCompileClasspath": {},
  "shared.scalablyTyped.runMvnDeps": {},
  "shared.mainClass": {},
  "shared.scalaJSOptimizer": {},
  "shared.scalaVersion": {},
  "shared.scalablyTyped.scalaOrganization": {},
  "shared.scalablyTyped.scalablyTypedFlavour": {},
  "shared.scalablyTyped.enablePluginScalacOptions": {},
  "shared.scalablyTyped.zincIncrementalCompilation": {},
  "shared.scalablyTyped.scalaJSBinaryVersion": {},
  "shared.scalaJSImportMap": {},
  "shared.scalacOptions": {},
  "shared.scalacPluginClasspath": {},
  "shared.jvmIndexVersion": {},
  "shared.allLocalMainClasses": {},
  "shared.localCompileClasspath": {},
  "shared.scalablyTyped.mvnDeps": {
    "shared.scalablyTyped.resolvedMvnDeps": {},
    "shared.resolvedRunMvnDeps": {},
    "shared.transitiveCoursierProjects": {},
    "shared.resolvedMvnDeps": {}
  },
  "shared.scalacPluginMvnDeps": {},
  "mill.scalajslib.worker.ScalaJSWorkerExternalModule.scalaJSWorker": {},
  "shared.scalaJSToolsClasspath": {},
  "shared.scalablyTyped.javacOptions": {},
  "shared.compileMvnDeps": {},
  "shared.scalablyTyped.jvmIndexVersion": {},
  "shared.compileClasspath": {},
  "shared.scalablyTyped.localRunClasspath.super.RunModule": {},
  "shared.jsEnvConfig": {}
}
real	0m23.823s
user	0m0.531s
sys	0m0.419s
```

If you rename 'assembly' to 'assemblyX' and update assemblyFe to call it, many fewer things
will be rebuilt:

```
[162/162] ============================== app.persistentAssembly ============================== 4s
{
  "mill.javalib.JvmWorkerModule.internalWorker": {},
  "mill.javalib.classgraph.ClassgraphWorkerModule.classgraphWorker": {},
  "app.assemblyX": {},
  "com.github.lolgab.mill.scalablytyped.ScalablyTypedWorkerApi.scalablyTypedWorker": {},
  "mill.scalajslib.worker.ScalaJSWorkerExternalModule.scalaJSWorker": {},
  "mill.javalib.classgraph.ClassgraphWorkerModule.classgraphWorkerClassloader": {}
}
real	0m4.527s
user	0m0.325s
sys	0m0.155s
```
