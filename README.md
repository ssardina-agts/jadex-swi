#############################################################################
# SWI-JADEX - A JADEX agent with SWI beliefs
#
# Copyright (C) 2013-2014 Sebastian Sardina, Thangarajah, Lin Padgham
#
# Contact: ssardina@gmail.com
#
# Version 1.0 - December 2013 
#	Developed with Rene Fischer research programmer
#############################################################################


INTRODUCTION
============

JADEX is one of the most developed, mature, and active agent platforms. 
However, agent beliefs are represented as standard Java data-structures. While
this is convenient to be able to re-use legacy software into an agent system,
it does not allow for powerful "database-like" representations of beliefs.

This project integrates the powerful SWI Prolog system into JADEX. By doing so,
it is possible to also use database/Prolog/Datalog type of belief representations.

The integration allows for interaction between the Java data structures and the 
SWI Prolog belief representation (e.g., Prolog can query the Java data structures 
if needed).


JADEX site: 
http://www.activecomponents.org/bin/view/About/Features 
http://sourceforge.net/projects/jadex/

SWI site: 
http://www.swi-prolog.org/



INSTALL & USAGE
===============

There are many ways to set up SWI-Jadex. Here is one that works.

1. install Java
---------------

* Java version 1.6
* download Sun's JDK
* extract it to ~/opt/
* point your `$JAVA_HOME` to the new JDK dir
* add the bin dir inside the JDK dir to your `$PATH`


2. install Jadex
----------------

* Jadex version 2.3
* see [http://www.activecomponents.org/bin/view/Download/Overview](http://www.activecomponents.org/bin/view/Download/Overview)
* download & extract it into ~/opt/


3. install SWI Prolog
---------------------

* SWI Prolog version 6.5
* see [http://www.swi-prolog.org/build/Debian.html](http://www.swi-prolog.org/build/Debian.html)
* make sure you install it **including** the JPL package
    * JPL version 3.0
    * separate JPL details can be found at [http://www.swi-prolog.org/packages/jpl/installation.html](http://www.swi-prolog.org/packages/jpl/installation.html)


4. install SWI-Jadex
--------------------

* SWI-Jadex version 1.0
* simply download the jar file from the Downloads section of the repository webpage on Bitbucket into ~/opt/
* -or- to build from source:
    * modify `build_conf.sh` to reflect your system's setup / install locations
    * run `build.sh` to compile the source


5. run the example agent
------------------------
* try running the example translation agent found in the SWI-Jadex bitbucket repository
    * run `run_example.sh` (make sure you've configured your `build_conf.sh`)
    * the JCC should pop up
    * load the Translation agent
    * start the agent
    * switch to the messaging center
    * set `Performative` to "request"
    * set `Protocol` to "fipa-request"
    * set the receiver (double click the Translation agent in the left pane)
    * set `Content` to "translate de es Hund"
    * hit `Send`
    * confirm that there are a bunch of messages on stdout ending in "... perro"
    * set `Content` to "synonyms en cat" & hit `Send`
    * confirm on stdout that there are no known synonyms for cat
    * send the message "add en de tiger Katze"
    * resend the message "synonyms en cat"
    * confirm that the Translation agent successfully inferred that "tiger" is a synonym of cat (since they both translate to "Katze" in german)


6. create a new Java project
----------------------------

* put the `jpl.jar` on the classpath
* put `swi-jadex-1.0.jar` on the classpath
* you may find it useful to use the example agent as starting point


troubleshooting
---------------

* `libjava.so`
    * if this can't be found, find the directory holding it and add that dir to `$LD_LIBRARY_PATH`
* `libjvm.so`
    * if this can't be found, then run `ldd path/to/your/libjava.so`, and if the output includes "`libjvm.so => not found`", then link it in (see `$LD_LIBRARY_PATH` and perhaps `$LD_PRELOAD`)



#############################################################################
# EOF
#############################################################################
