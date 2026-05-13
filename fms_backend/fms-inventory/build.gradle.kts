plugins {
    `java-library`
}

dependencies {
    implementation(project(":fms-common"))
    implementation(project(":fms-user"))
    compileOnly("org.projectlombok:lombok")
    annotationProcessor("org.projectlombok:lombok")
}
