
## Writing documentation

http://projectmallard.org/ducktype/1.0/index.html

## Building documentation

Building the documentation requires the 'ducktype' utility. The makefile will use this
command to generate a directory of .page files from the .duck files in the /duck directory.

    $ sudo apt install ducktype
    $ make

## Reading documentation

After generating the documentation it can be previewed by running yelp on the /pages directory:

    $ yelp pages

## Installing documentation

After making changes to the documentation, run 'make install' to update the set
of .page files in the citadel-documentation recipe.

    $ make install

