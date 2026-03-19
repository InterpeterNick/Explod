#!/usr/bin/env kotlin

import java.nio.file.Files
import java.nio.file.Path

var i: Int = 0

Files.walk(Path.of("resources", "item")).forEach { p -> i++ }

println(i)