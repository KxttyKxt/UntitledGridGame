plugins {
    id("java")
}

group = "com.kxttykxt.ugg"
version = "infdev"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    implementation(project(":UGG"))

    // https://mvnrepository.com/artifact/com.jayway.jsonpath/json-path
    implementation("com.jayway.jsonpath:json-path:2.9.0")

    // https://mvnrepository.com/artifact/commons-io/commons-io
    implementation("commons-io:commons-io:2.18.0")

    // https://mvnrepository.com/artifact/org.slf4j/slf4j-nop
    implementation("org.slf4j:slf4j-nop:2.0.17")
}

tasks.test {
    useJUnitPlatform()
}