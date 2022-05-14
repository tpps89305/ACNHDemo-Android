package com.dispy.acnhdemo.model

import android.content.Context

/**
 * Created by yangchaofu on 2022/2/1.
 *
 * @author yangchaofu
 * @since 2022/2/1
 */
class ResourceHandler {
    companion object {
        fun getResourceId(context: Context, resourceName: String, resourceType: String = "drawable"): Int {
            return context.resources.getIdentifier(resourceName, resourceType, context.packageName)
        }
    }
}