# Alien Mod (Fabric, Minecraft 26.3)

Grey alien mob with a custom 3D model (big pear-shaped head, slanted black eyes, thin ribbed torso, long arms).

Requirements: Java 25, Gradle 9.6+, Fabric Loader 0.19.5, Fabric API 0.161.0+26.3.

Build:  gradle build   (jar in build/libs/)
Test:   gradle runClient, then /summon alienmod:alien or use the Alien Spawn Egg (creative > spawn eggs)

Model:   src/client/java/com/alienmod/client/AlienModel.java
Texture: src/main/resources/assets/alienmod/textures/entity/alien.png (64x64, regenerate with make_texture.py)
