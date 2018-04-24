import copy
import math
import random
import binascii

class Packet:
    indexGenerator = 0
    PACKET_SIZE = 8
    INDEX_SIZE = 20
    def __init__(self, bits):
        bity = bin(bits)[2:]
        self.data = '0' * Packet.PACKET_SIZE
        self.data = self.data[len(bity):] + bity
        self.checksum = '0' * (int((math.floor(math.log(Packet.PACKET_SIZE, 2)))) + 1)
        count = 0
        for bit in self.data:
            if (bit == '1'):
                count += 1
        suma = bin(count)[2:]
        self.checksum = self.checksum[len(suma):] + suma
        self.generateIndex = bin(Packet.indexGenerator)[2:]
        self.index = '0' * Packet.INDEX_SIZE
        self.index = self.index[len(self.generateIndex):] + self.generateIndex
        Packet.indexGenerator = Packet.indexGenerator + 1
        data = []
        checksum = []
        index = []
        for i, bit in enumerate(self.data):
            data.append(int(self.data[i]))
        for i, bit in enumerate(self.checksum):
            checksum.append(int(self.checksum[i]))
        for i, bit in enumerate(self.index):
            index.append(int(self.index[i]))
        self.data = data
        self.checksum = checksum
        self.index = index
        self.originalCopy = copy.deepcopy(self.data)

    def damageData(self, possibility):
        for i, bit in enumerate(self.data):
            x = random.random()
            if x < possibility:
                if bit == 0:
                    self.data[i] = 1
                else:
                    if (bit == 1):
                        self.data[i] = 0

    def checkIfDamaged(self):
        count = 0
        for bit in self.data:
            if (bit == 1):
                count += 1
        zmienna = 0
        for i, bit in enumerate(self.checksum):
            zmienna = zmienna + self.checksum[i] * pow(2, (int((math.floor(math.log(Packet.PACKET_SIZE, 2))-1))+1) - i)
        if zmienna == count:
            return False
        else:
            return True

    def getIndex(self):
        zmienna = 0
        for i, bit in enumerate(self.index):
            zmienna = zmienna + self.index[i] * pow(2, 19 - i)
        return zmienna

    def setPacketSize(self, x, y):
        Packet.PACKET_SIZE = x
        Packet.INDEX_SIZE = y

    def checkIfDamagedCRC(self):

        for bit, i in enumerate(self.data):
            if binascii.crc32(bytes(self.data[i])) != binascii.crc32(bytes(self.originalCopy[i])):
                return True

        return False
