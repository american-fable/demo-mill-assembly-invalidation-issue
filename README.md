Name of 'assembly' function causing difference in dependent tasks invalidation
===

## How to reproduce issue

Run this to install npm dependencies.

``` sh
./mill app.npmInstall
```

Then run the command twice and verify that the second time it runs fast and rebuilds nothing

``` bash
time (./mill app.persistentAssembly && cat out/mill-invalidation-tree.json)
```

On first run output will looks like this:

```

[162/162] ============================== app.persistentAssembly ============================== 17s
{
  ...
  "app.assemblyFe": {},
  ...
  "mill.scalajslib.worker.ScalaJSWorkerExternalModule.scalaJSWorker": {
    "worker.fastLinkJS": {
      "app.assembly": {}
    }
  },
  ...
}
real    0m18.432s
user    0m0.375s
sys     0m0.269s

```

On second call it should looks like this: 

```
[162/162] ============================== app.persistentAssembly ==============================
{}
real    0m0.241s
user    0m0.173s
sys     0m0.057s

```

Now change the log line in app.assembly/assembly build.mill and run the
command again. When the task is called assembly (and assemblyFe calls it), many things are rebuilt, including scalablytyped. If you rename 'assembly' to 'assemblyX' and assemblyFe calls it, the whole thing will take much less time because is not invalidation of `worker.fastLinkJS`. 
Expected output in second case should looks like this:

```
[162/162] ============================== app.persistentAssembly ============================== 2s
{
  "mill.javalib.JvmWorkerModule.internalWorker": {},
  "mill.javalib.classgraph.ClassgraphWorkerModule.classgraphWorker": {},
  "app.assemblyX": {},
  "com.github.lolgab.mill.scalablytyped.ScalablyTypedWorkerApi.scalablyTypedWorker": {},
  "mill.scalajslib.worker.ScalaJSWorkerExternalModule.scalaJSWorker": {},
  "mill.javalib.classgraph.ClassgraphWorkerModule.classgraphWorkerClassloader": {}
}
real    0m3.090s
user    0m0.251s
sys     0m0.102s

```

# Why renaming of task causing difference in invalidation???


## You could use Nix to open shell with right dependencies 

``` sh
nix develop
```
