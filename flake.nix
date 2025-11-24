{
  description = "nix development shell";

  inputs = {
    nixpkgs.url = "nixpkgs/nixos-unstable";
    utils.url = "github:numtide/flake-utils";
  };

  outputs =
    {
      self,
      nixpkgs,
      utils,
    }:
    utils.lib.eachDefaultSystem (
      system:
      let
        pkgs = import nixpkgs { inherit system; };

        packagesList = with pkgs; [
          curl
          git
          less
          nodejs_20
          which
          fd
          coursier
          openjdk21
        ];
      in
      {
        devShells.default = pkgs.mkShell { buildInputs = packagesList; };

        packages = {
          default = pkgs.hello;
        };
      }
    );
}
