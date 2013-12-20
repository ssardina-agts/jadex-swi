#!/bin/bash


########################################################
# ADJUST THE FOLLOWING VARIABLES TO REFLECT YOUR SETUP #
########################################################


# change this if you change the version of SWI-Jadex
SWI_JADEX_VERSION=6.2.6

# point this to your jadex directory. It should contain a lib directory containing a number of jar files, like jadex-bridge-2.3.jar
JADEX_DIR=/opt/jadex

# point this towards your SWI-Prolog directory
SWI_DIR=/usr/lib/swi-prolog

# point this towards your jpl jar file
JPL_JAR=$SWI_DIR/lib/jpl.jar

# point this towards the directory containing the libjpl.so file
LIBJPL_DIR=$SWI_DIR/lib/amd64

# point this towards your junit jar file
#JUNIT_JAR=~/opt/eclipse/plugins/org.junit_4.10.0.v4_10_0_v20120426-0900/junit.jar
JUNIT_JAR=/usr/share/java/junit.jar

# point this towards your hamcrest jar file
HAMCREST_JAR=/usr/share/eclipse/dropins/jdt/plugins/org.hamcrest.core_1.3.0.jar
