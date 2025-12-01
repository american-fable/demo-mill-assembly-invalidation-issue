Conditional JS linker execution
===

## How to reproduce issue

Run this to install npm dependencies.

``` sh
(./mill --jobs=0 app.npmInstall + app.persistentAssembly && cat out/mill-invalidation-tree.json)
```

as result, something like this will be in invalidation tree, which means that
both tasks called no matter what in condition was. That possible to avoid if
everything needed can known beforehand so separate tasks would used for
selecting. But in this specific case it looks like simpler solution could e
possible if instead calling full/fast LinkJS that could be specified as
parameter like `linkJSTask(isFullLinkJS: Boolean = false)`.


```
 "worker.scalablyTyped.localClasspath": {
    "worker.transitiveLocalClasspath": {
      "worker.runClasspath": {
        "worker.fastLinkJS": {},
        "worker.fullLinkJS": {}
      }
    }
  }

 ...

 "worker.runClasspath": {
    "worker.fastLinkJS": {},
    "worker.fullLinkJS": {}
 }
```


## You could use Nix to open shell with right dependencies 

``` sh
nix develop
```
