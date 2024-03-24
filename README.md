# Height Map Mod

## Setup

Move jar file into /mods folder

## Instructions

Steps to export CSV height map:
1. Load your world
2. Find out coordinates of two points, start and end [x1, z2] [x1, z2] and _step_, in example _step_ = 4
3. Use command `/height [x1,z1] [x2,z2] <scale>` to export _.csv_ file
4. Look up _.csv_ file into your _.minecraft_ folder

## Example

`/height -2 -6 -10 -14 4`
Where

[-2, -6] - 1st coordinates

[-10, -14] - 2nd coordinates

[4] - scale

Yellow wool used for clarity of which heights will be exported
![12314](https://github.com/Harrota/height-map-mod/assets/44808070/bf8ff5eb-8fee-4682-b8e6-ef9c14b94ead)
![image](https://github.com/Harrota/height-map-mod/assets/44808070/d8545faf-75e4-45c4-a78b-460dfabd3c0d)
