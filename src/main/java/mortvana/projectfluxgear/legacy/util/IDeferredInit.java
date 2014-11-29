package mortvana.projectfluxgear.legacy.util;

import mortvana.projectfluxgear.legacy.ContentRegistry;

/**
 * Doesn't appear to work. Do not use.
 */
@Deprecated
// TODO: Ask Gyro why we need this
public interface IDeferredInit {
    //To be called after every configgable has been configured.
    void DeferredInit(ContentRegistry cr);
}