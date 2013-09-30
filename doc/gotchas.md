ADF expressions
---

the interpolated parameter doesn't get interpolated if it's inside a java string, so use `"..." + $param + "..."`


module ordering
---

in prolog there is a difference between

    module:assert(predicate).
    assert(module:predicate).

leave module selection up to the user?
provide assert methods in the prologengine?

    engine.asserta(predicate);
    engine.assertz(predicate);
    engine.retract(predicate);

or is there?! http://www.lix.polytechnique.fr/~catuscia/teaching/prolog/Manual/sec-4.6.html suggests otherwise at the very bottom
see also the @ predicate, allowing predicate@module: http://www.swi-prolog.org/pldoc/doc_for?object=%28@%29/2
it seems there is a difference between doing it directly in swipl vs doing it through java/eclipse/jpl. Further debugging is necessary.


symbol lookup error: /usr/lib/swi-prolog/lib/amd64/memfile.so: undefined symbol: PL_query
---

this happens when you try to `use_module(library(http/json_convert))`
    which in turn tries to `use_module(json)`
        which in turn tries to `use_module(library(memfile))`
            which eclipse/jvm can not locate (even though it shows the location of the file in the error message!?).


solution:
    ???

calling into java from prolog cli, an error occurs
---

it looks like:
    ERROR: /usr/lib/swi-prolog/library/jpl.pl:4637:
    '$open_shared_object'/3: libjvm.so: cannot open shared object file: No such file or directory
    ERROR: /usr/lib/swi-prolog/library/jpl.pl:4637:
        library 'java' does not exist (Please add directory holding libjava.so to $LD_LIBRARY_PATH)

solution: 
    $ `locate libjvm.so`
    $ `locate libjpl.so`
    $ # export their paths in LD_LIBRARY_PATH
    $ `export LD_LIBRARY_PATH=~/opt/jdk1.7.0_21/jre/lib/amd64/:~/opt/jdk1.7.0_21/jre/lib/amd64/server/`


JRef is deprecated
---

use it anyway, in line with JPL developer Paul Singleton's advice at http://osdir.com/ml/lang.swi-prolog.general/2007-08/msg00071.html
The class is deprecated but the concept is valid. Eventually, it will be changed to something more robust, at which point we can adjust
our code. But it will not go away.
