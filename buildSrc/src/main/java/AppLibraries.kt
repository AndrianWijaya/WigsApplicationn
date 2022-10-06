import org.gradle.api.artifacts.dsl.DependencyHandler
import dependencies.*

fun DependencyHandler.appLibraries() {
    androidX()
    glide()
    gson()
    daggerHilt()
    gander()
    materialDesign()
    okHttp()
    retrofit()
    vmLifeCycle()
    coroutine()
}