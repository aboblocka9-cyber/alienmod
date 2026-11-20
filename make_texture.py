import zlib, struct, random
random.seed(7)
W=H=64
px=[[(0,0,0,0)]*W for _ in range(H)]
def fill(x0,y0,x1,y1,base,noise=6):
    for y in range(y0,y1):
        for x in range(x0,x1):
            n=random.randint(-noise,noise)
            px[y][x]=tuple(max(0,min(255,c+n)) for c in base)+(255,)
GREY=(138,138,132); DARK=(96,96,92); BLACK=(8,8,10)
fill(0,0,40,17,GREY)        # cranium
fill(0,17,28,28,GREY)       # jaw
fill(0,28,18,40,GREY)       # torso
fill(0,40,36,56,GREY)       # limbs
# skin tone variation: darker top/bottom
for x in range(0,40):
    for y in (0,1,2,3,4,5,6,7,8,9):
        if y<10 and x<40 and px[y][x][3]: pass
# eyes (shared UV region 40..48 x 0..4)
fill(40,0,48,4,BLACK,2)
px[1][41]=(70,70,80,255); px[1][45]=(70,70,80,255)
# cranium front face = x10..20,y10..17 : nostrils + faint brow
px[14][14]=DARK; px[14][15]=DARK
# jaw front = x8..14,y25..28 : thin mouth with teeth
for x in range(9,13): px[26][x]=(40,20,25,255)
px[27][9]=(210,210,200,255); px[27][11]=(210,210,200,255)
# torso front = x3..9,y31..40 : visible ribs
for y in (32,34,36): 
    for x in range(3,9): px[y][x]=DARK
for y in range(31,40): px[y][6]=DARK
# knees/elbows darker joints
for x in range(0,36): px[48][x]=DARK if px[48][x][3] else px[48][x]
raw=b''.join(b'\x00'+bytes(c for p in row for c in p) for row in px)
def chunk(t,d): 
    c=struct.pack('>I',len(d))+t+d
    return c+struct.pack('>I',zlib.crc32(t+d)&0xffffffff)
png=b'\x89PNG\r\n\x1a\n'+chunk(b'IHDR',struct.pack('>IIBBBBB',W,H,8,6,0,0,0))+chunk(b'IDAT',zlib.compress(raw,9))+chunk(b'IEND',b'')
open('src/main/resources/assets/alienmod/textures/entity/alien.png','wb').write(png)
