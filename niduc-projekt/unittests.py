from unittest import TestCase
from Packet import Packet
from imageReader import loadImage
import numpy as np


class Test(TestCase):
    def setUp(self):
        self.correctFileName = 'lena.bmp'

    def test_damageDataCorrect(self):
        data = Packet(123)
        data.damageData(1)
        self.assertEqual(data.checkIfDamaged(), True)

    def test_damageDataIncorrect(self):
        data = Packet(123)
        data.damageData(0)
        self.assertEqual(data.checkIfDamaged(), False)

    def test_damagedDataTestCorrect(self):
        data = Packet(123)
        data.damageData(1)
        self.assertEqual(data.checkIfDamagedCRC(), True)

    def test_damagedDataTestIncorrect(self):
        data = Packet(123)
        data.damageData(0)
        self.assertEqual(data.checkIfDamagedCRC(), False)

    def test_packetCreation(self):
        data = Packet(123)
        self.assertIsInstance(data, Packet)

    def test_loadImageCorrect(self):
        data = loadImage(self.correctFileName)
        self.assertIsInstance(data, np.ndarray)

    def test_loadImageIncorrect(self):
        with self.assertRaises(FileNotFoundError):
            data = loadImage("123.cos")

    def test_getIndex(self):
        data = Packet(123)
        zmienna = 0
        for i, bit in enumerate(data.index):
            zmienna = zmienna + data.index[i] * pow(2, 19 - i)
        self.assertEqual(zmienna, data.getIndex())

    def test_checkChecksum(self):
        data = Packet(123)
        count = 0
        for bit in data.data:
            if bit == 1:
                count += 1
        zmienna = 0
        for i, bit in enumerate(data.checksum):
            zmienna = zmienna + data.checksum[i] * pow(2, (int((np.math.floor(
                np.math.log(Packet.PACKET_SIZE, 2)) - 1)) + 1) - i)
        self.assertEqual(zmienna, count)


