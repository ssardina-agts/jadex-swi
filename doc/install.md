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
* simply download the jar file from the Bitbucket repository into ~/opt/


5. create a new Java project
----------------------------

* put the `jpl.jar` on the classpath
* put `swi-jadex-1.0.jar` on the classpath
* try running the example translation agent found in the SWI-Jadex bitbucket repository


troubleshooting
---------------

* `libjava.so`
    * if this can't be found, find the directory holding it and add that dir to `$LD_LIBRARY_PATH`
* `libjvm.so`
    * if this can't be found, then run `ldd path/to/your/libjava.so`, and if the output includes "`libjvm.so => not found`", then link it in (see `$LD_LIBRARY_PATH` and perhaps `$LD_PRELOAD`)

