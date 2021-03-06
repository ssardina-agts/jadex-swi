SWI-Jadex supports four use cases for agents sharing knowledge bases.

On the one hand, we want to allow an agent to have its own personal/private knowledge. This might be knowledge that only pertains to itself or knowledge that is secret. On the other hand, we want to allow an agent to share a knowledgebase with another agent. And finally, an agent might want to keep multiple separate knowledgebases.

Each agent can have its own knowledgebase
-----------------------------------------

In Agent X's ADF:

    <imports>
        <import>au.edu.rmit.csit.swijadex.PrologEngine</import>
        <import>au.edu.rmit.csit.swijadex.JPLEngineFactory</import>
    </imports>

    <beliefs>
        <belief name="prolog knowledgebase A">
            <fact>new JPLEngineFactory().buildPrologEngine()</fact>
        </belief>
    </beliefs>

In Agent Y's ADF:

    <imports>
        <import>au.edu.rmit.csit.swijadex.PrologEngine</import>
        <import>au.edu.rmit.csit.swijadex.JPLEngineFactory</import>
    </imports>

    <beliefs>
        <belief name="prolog knowledgebase B">
            <fact>new JPLEngineFactory().buildPrologEngine()</fact>
        </belief>
    </beliefs>

An agent can have multiple knowledgebases
-----------------------------------------

In Agent X's ADF:

    <imports>
        <import>au.edu.rmit.csit.swijadex.PrologEngine</import>
        <import>au.edu.rmit.csit.swijadex.JPLEngineFactory</import>
    </imports>

    <beliefs>
        <belief name="prolog knowledgebase A">
            <fact>new JPLEngineFactory().buildPrologEngine()</fact>
        </belief>
        <belief name="prolog knowledgebase B">
            <fact>new JPLEngineFactory().buildPrologEngine()</fact>
        </belief>
    </beliefs>


Multiple agents can share a single knowledgebase
------------------------------------------------

In Agent X's ADF:

    <imports>
        <import>au.edu.rmit.csit.swijadex.PrologEngine</import>
        <import>au.edu.rmit.csit.swijadex.JPLEngineFactory</import>
    </imports>

    <beliefs>
        <belief name="prolog knowledgebase A">
            <fact>new JPLEngine("shared_kb")</fact>
        </belief>
    </beliefs>

In Agent Y's ADF:

    <imports>
        <import>au.edu.rmit.csit.swijadex.PrologEngine</import>
        <import>au.edu.rmit.csit.swijadex.JPLEngineFactory</import>
    </imports>

    <beliefs>
        <belief name="prolog knowledgebase A">
            <fact>new JPLEngine("shared_kb")</fact>
        </belief>
    </beliefs>


Multiple agents can share multiple knowledgebases
-------------------------------------------------

In Agent X's ADF:

    <imports>
        <import>au.edu.rmit.csit.swijadex.PrologEngine</import>
        <import>au.edu.rmit.csit.swijadex.JPLEngineFactory</import>
    </imports>

    <beliefs>
        <belief name="prolog knowledgebase A">
            <fact>new JPLEngine("share_this")</fact>
        </belief>
        <belief name="prolog knowledgebase B">
            <fact>new JPLEngine("share_that")</fact>
        </belief>
    </beliefs>

In Agent Y's ADF:

    <imports>
        <import>au.edu.rmit.csit.swijadex.PrologEngine</import>
        <import>au.edu.rmit.csit.swijadex.JPLEngineFactory</import>
    </imports>

    <beliefs>
        <belief name="prolog knowledgebase A">
            <fact>new JPLEngine("share_this")</fact>
        </belief>
        <belief name="prolog knowledgebase B">
            <fact>new JPLEngine("share_that")</fact>
        </belief>
    </beliefs>


