#!/bin/bash


########################################################
# This script compiles your source files 
#
# ADJUST THE FILE build_conf.sh TO REFLECT YOUR SETUP
########################################################

. "`pwd`/build_conf.sh"

#########################################################
# YOU SHOULDN'T NEED TO CHANGE ANYTHING BELOW THIS LINE #
#########################################################

JADEX_LIB_DIR=$JADEX_DIR/lib
OUTPUT_DIR=target/classes
PACKAGE_AS_DIR=au/edu/rmit/csit/swijadex
EXAMPLE_PACKAGE_AS_DIR=$PACKAGE_AS_DIR/example/translation
JADEX_JARS=$JADEX_LIB_DIR/activation-1.1.jar:$JADEX_LIB_DIR/antlr-2.7.7.jar:$JADEX_LIB_DIR/antlr-runtime-3.1.3.jar:$JADEX_LIB_DIR/asm4-4.0.jar:$JADEX_LIB_DIR/bcprov-jdk15-140.jar:$JADEX_LIB_DIR/collections-generic-4.01.jar:$JADEX_LIB_DIR/colt-1.2.0.jar:$JADEX_LIB_DIR/concurrent-1.3.4.jar:$JADEX_LIB_DIR/de.huxhorn.sulky.3rdparty.jlayer-1.0.jar:$JADEX_LIB_DIR/gluegen-rt-1.jar:$JADEX_LIB_DIR/jadex-applib-bdi-2.3.jar:$JADEX_LIB_DIR/jadex-applications-bdi-2.3.jar:$JADEX_LIB_DIR/jadex-applications-bdibpmn-2.3.jar:$JADEX_LIB_DIR/jadex-applications-bdiv3-2.3.jar:$JADEX_LIB_DIR/jadex-applications-bpmn-2.3.jar:$JADEX_LIB_DIR/jadex-applications-gpmn-2.3.jar:$JADEX_LIB_DIR/jadex-applications-micro-2.3.jar:$JADEX_LIB_DIR/jadex-bridge-2.3.jar:$JADEX_LIB_DIR/jadex-commons-2.3.jar:$JADEX_LIB_DIR/jadex-javaparser-2.3.jar:$JADEX_LIB_DIR/jadex-kernel-application-2.3.jar:$JADEX_LIB_DIR/jadex-kernel-base-2.3.jar:$JADEX_LIB_DIR/jadex-kernel-bdi-2.3.jar:$JADEX_LIB_DIR/jadex-kernel-bdibpmn-2.3.jar:$JADEX_LIB_DIR/jadex-kernel-bdiv3-2.3.jar:$JADEX_LIB_DIR/jadex-kernel-bpmn-2.3.jar:$JADEX_LIB_DIR/jadex-kernel-component-2.3.jar:$JADEX_LIB_DIR/jadex-kernel-extension-agr-2.3.jar:$JADEX_LIB_DIR/jadex-kernel-extension-envsupport-2.3.jar:$JADEX_LIB_DIR/jadex-kernel-extension-envsupport-opengl-2.3.jar:$JADEX_LIB_DIR/jadex-kernel-gpmn-2.3.jar:$JADEX_LIB_DIR/jadex-kernel-micro-2.3.jar:$JADEX_LIB_DIR/jadex-model-bpmn-2.3.jar:$JADEX_LIB_DIR/jadex-nuggets-2.3.jar:$JADEX_LIB_DIR/jadex-platform-2.3.jar:$JADEX_LIB_DIR/jadex-platform-standalone-launch-2.3.jar:$JADEX_LIB_DIR/jadex-rules-2.3.jar:$JADEX_LIB_DIR/jadex-rules-applications-2.3.jar:$JADEX_LIB_DIR/jadex-rules-eca-2.3.jar:$JADEX_LIB_DIR/jadex-rules-tools-2.3.jar:$JADEX_LIB_DIR/jadex-runtimetools-swing-2.3.jar:$JADEX_LIB_DIR/jadex-tools-base-2.3.jar:$JADEX_LIB_DIR/jadex-tools-base-swing-2.3.jar:$JADEX_LIB_DIR/jadex-tools-bdi-2.3.jar:$JADEX_LIB_DIR/jadex-tools-bpmn-2.3.jar:$JADEX_LIB_DIR/jadex-tools-comanalyzer-2.3.jar:$JADEX_LIB_DIR/jadex-xml-2.3.jar:$JADEX_LIB_DIR/janino-2.5.10.jar:$JADEX_LIB_DIR/javassist-3.12.1.GA.jar:$JADEX_LIB_DIR/jcalendar-1.3.2.jar:$JADEX_LIB_DIR/jcommon-1.0.15.jar:$JADEX_LIB_DIR/jfreechart-1.0.12.jar:$JADEX_LIB_DIR/jogl-1.jar:$JADEX_LIB_DIR/jung-algorithms-2.0.1.jar:$JADEX_LIB_DIR/jung-api-2.0.1.jar:$JADEX_LIB_DIR/jung-graph-impl-2.0.1.jar:$JADEX_LIB_DIR/jung-visualization-2.0.1.jar:$JADEX_LIB_DIR/junit-3.8.1.jar:$JADEX_LIB_DIR/mail-1.4.5.jar:$JADEX_LIB_DIR/smack-3.1.0.jar:$JADEX_LIB_DIR/smackx-3.1.0.jar:$JADEX_LIB_DIR/stringtemplate-3.2.jar:$JADEX_LIB_DIR/xpp3-1.1.4c.jar

CLASSPATH=$OUTPUT_DIR:$JUNIT_JAR:$HAMCREST_JAR:$JPL_JAR:$JADEX_JARS

javac -classpath $CLASSPATH -d $OUTPUT_DIR src/main/java/$PACKAGE_AS_DIR/*.java src/example/java/$EXAMPLE_PACKAGE_AS_DIR/*.java

cp src/example/java/$EXAMPLE_PACKAGE_AS_DIR/Translation.agent.xml $OUTPUT_DIR/$EXAMPLE_PACKAGE_AS_DIR/

cp src/example/prolog/* $OUTPUT_DIR/
