import PIL.Image as Image
import numpy as np

def toBinary(integer):
    return [int(x) for x in list('{0:008b}'.format(integer))]

def toDecimal(integer):
    out = 0
    for bit in integer:
        out = (out << 1) | bit
    return out

def dataToBinary(data):
    d = data.copy()
    print(type(d))
    print(d)

def size():
    data = loadImage("rejs.bmp")
    return data.size

def loadImage(fileName):
    img = Image.open(fileName)
    img.load()
    data = np.asarray(img, dtype="int32")
    return data

def saveImage(data, fileName):
    # img = Image.fromarray(np.asarray(np.clip(data, 0, 255), dtype="uint8"), "L")
    # img = Image.fromarray(data, "L")
    img = Image.fromarray(np.asarray(data, dtype="uint8"), "P")
    img.save(fileName)

