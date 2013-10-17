java objects inside prolog
    what happens when they are changed on the java side?
    what happens when they are changed on the prolog side?
    is there some notification?
    Are they mirrored at the time of transfer and then any changes to one side but not the other diverge them?
    are they read-only?
    is there master/slave?
    solution for now: leave it up to the user. 
        If they change one and want the other to reflect that, they can manually remove the old copy and retransfer the changed version
        If they change one and want to leave the other unchanged, simply do nothing to the other.
    alternative solution
        have the prolog side store no values, but simply call back into java

Event handling
    sources of events
        plans firing event
        timers expiring
        other agents
        external input
        prolog facts being changed by other agents
    subscribers to events
        plan triggers in ADF
        conditions based on beliefs
        queries based on beliefs
