import com.varabyte.kobweb.gradle.application.util.configAsKobwebApplication
import com.varabyte.kobweb.project.conf.KobwebConfFile
import kotlin.io.path.createFile
import kotlin.io.path.writeText
import com.varabyte.kobweb.gradle.application.kobwebFolder

plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.plugin.compose)
    alias(libs.plugins.kobweb.application)
    alias(libs.plugins.kobwebx.markdown)
}

group = "me.e_psi_lon.website"
version = "1.0-SNAPSHOT"

kobweb {
    app {
        index {
            description.set("Powered by Kobweb")
        }

        // Only legacy sites need this set. Sites built after 0.16.0 should default to DISALLOW.
        // See https://github.com/varabyte/kobweb#legacy-routes for more information.
    }
}

kotlin {
    configAsKobwebApplication("website")

    sourceSets {
        commonMain.dependencies {
            implementation(libs.compose.runtime)
        }

        jsMain.dependencies {
            implementation(libs.compose.html.core)
            implementation(libs.kobweb.core)
            implementation(libs.kobweb.silk)
            implementation(libs.silk.icons.fa)
            implementation(libs.kobwebx.markdown)
            
        }
    }
}

// Une task nommee createCNAME
tasks.register("createCNAME") {
    doLast {
        val kobwebConfFile = KobwebConfFile(kobwebFolder)
        val siteRoot = kobwebConfFile.content?.server?.files?.prod?.siteRoot
        println(siteRoot)
        if (siteRoot != null) {
            val projectDir = project.projectDir.toPath()
            val siteRootPath = projectDir.resolve(siteRoot)
            println(siteRootPath)
            val cnameFile = siteRootPath.resolve("CNAME")
            cnameFile.createFile()
            cnameFile.writeText("e-psi-lon.me")
        }
    }
}

tasks.named("kobwebExport") {
    this.finalizedBy("createCNAME")
}
