Requirements
============

* User should be able to use Prolog syntax inside Jadex (plan bodies, ADF beliefs and expressions)
    * There are Prolog parsers written in Java (though it seems silly to parse Prolog to Java to then generate Prolog from Java)
* User should be able to put entire objects into Prolog, not just simple key/value pairs
    either as an opaque reference
        see InvisibleObject in InterProlog
    or as deserialised objects
        see teachObject in InterProlog
* Interface can be via JNI or sockets
    JNI
        provides better performance
        provides only single prolog instance (use modules to fake multiple knowledge bases)
        see JPL
    sockets
        provide distributability
        are possibly easier to debug
        provide multiple prolog instances
        see InterProlog
* Belief changes inside Prolog should trigger event listeners in Jadex
    normally, jadex event handling is done Bean-style, but since it isn't attributes on the PrologBridge instance that are changing, this won't work cleanly.
    perhaps, from prolog, we need to pass events to java, then, somehow, map those events to triggers in jadex (e.g. plan triggers)
* Multiple agents should be able to share a knowledgebase
* Multiple agents should be able to have separate knowledgebases
* A Single agent should be able to have multiple knowledgebases
* User should be able to use SWI-Jadex in multithreaded programs
* An agent should be able to use a knowledgebase running on a separate computer

* Will we want to support Prologs other than SWI in the future?
* Will we want to support some way of debugging Prolog through the Jadex Control Center?
