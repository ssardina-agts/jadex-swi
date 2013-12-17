#!/bin/bash

#######################################################
# this script will package the source into a jar file #
#                                                     #
# configure the version in build_conf.sh              #
#######################################################

. "`pwd`/build.sh"

cd $OUTPUT_DIR && jar cvf swi-jadex.jar au/edu/rmit/csit/swijadex/*.class && cd -

mv $OUTPUT_DIR/swi-jadex.jar swi-jadex-$SWI_JADEX_VERSION.jar
