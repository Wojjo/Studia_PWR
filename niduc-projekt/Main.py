import copy

from imageReader import *
from Packet import Packet

def menu():
    choice = '1'
    nazwa = str
    obraz = int
    damageSize = int
    zbiorPakietow = []
    kolejka = []
    kolejkaZwrotna = []
    pakiety = []
    # xSize = int
    # ySize = int
    efektPrawieKoncowy = []
    while choice != '0':
        print("Co chcesz zrobić?\n")
        print("1. Wczytaj plik\n2. Ustaw prawdopodobienstwo bledu\n3. Przesylaj plik za pomoca StopAndWait")
        print("4. Przesylaj plik za pomoca GoBackN\n5. Zapisz plik\n6. Wyswietl pomiary\n\n0. Wyjscie\n\nTwoj wybor: ")
        choice = input()
        if (choice == '1'):
            print("\nPodaj nazwe pliku: ")
            nazwa = input()
            # nazwa = "lena.bmp"
            obraz = loadImage(nazwa)
            xSize = obraz[0].size
            ySize = int(obraz.size / xSize)
            for line in obraz:
                for element in line:
                    pakiety.append(Packet(int(element)))
            print("\nObraz załadowany pomyślnie!")
            # print(pakiety[100].checkIfDamaged())
            # pakiety[100].damageData(0.01)
            # print(pakiety[100].checkIfDamaged())
            # print(pakiety[100].getIndex())
        elif (choice == '2'):
            print("Podaj szansę na wystąpienie błędu (0 - 100%): ")
            damageSize = int(input())
            # damageSize = 10.01
            damageSize = damageSize / 100
        elif (choice == '3'):
            notEnd = True
            canSend = True
            iterator = 0
            packetsSent = 0
            packetsReceived = 0
            packetsRetransmitted = 0
            damagedPacketsSent = 0
            sent = []
            kolejka.append(0)
            kolejka.append(0)
            kolejka.append(0)
            kolejka.append(0)
            kolejkaZwrotna.append(0)
            kolejkaZwrotna.append(0)
            kolejkaZwrotna.append(0)
            kolejkaZwrotna.append(0)
            print(len(kolejka))
            while notEnd:
                if kolejkaZwrotna[len(kolejkaZwrotna) - 1] == 2:
                    packetsSent = packetsSent - 1
                    print("Otrzymano polecenie retransmiji")
                    packetsRetransmitted = packetsRetransmitted + 1
                    canSend = True
                if kolejkaZwrotna[len(kolejkaZwrotna) - 1] == 1:
                    print("Otrzmano żądanie kolejnego pakietu")
                    canSend = True
                kolejkaZwrotna.pop()
                if canSend:
                    toSend = copy.deepcopy(pakiety[packetsSent])
                    toSend.damageData(damageSize)
                    packetsSent = packetsSent + 1
                    print("Wyslano pakiet nr " + str(packetsSent))
                if toSend != 0:
                    sent.insert(0, toSend)
                    toSend = 0
                    canSend = False
                    kolejka.insert(0, 1)
                else:
                    kolejka.insert(0, 0)

                if kolejka[len(kolejka) - 1] == 1:
                    recieved = sent[0]
                    sent.pop()
                    if not recieved.checkIfDamaged():
                        if recieved.checkIfDamagedCRC():
                            damagedPacketsSent = damagedPacketsSent + 1

                        packetsReceived = packetsReceived + 1
                        zmienna = 0
                        for i, bit in enumerate(recieved.data):
                            zmienna = zmienna + recieved.data[i] * pow(2, Packet.PACKET_SIZE - 1 - i)
                        efektPrawieKoncowy.append(zmienna)
                        kolejkaZwrotna.insert(0, 1)
                        print("Otrzymano pakiet nr " + str(packetsReceived))
                        print("Wysylam żądanie następnego pakietu")
                    elif recieved.checkIfDamaged():
                        kolejkaZwrotna.insert(0, 2)
                        packetsRetransmitted = packetsRetransmitted + 1
                        print("Pakiet uszkodzony! Wysyłam żądanie retransmisji")
                    print("Upłynęło jednostek czasu " + str(iterator))
                elif kolejka[len(kolejka) - 1] == 0:
                    # kolejkaZwrotna.append(0)
                    kolejkaZwrotna.insert(0, 0)
                kolejka.pop()
                iterator = iterator + 1
                if packetsReceived == obraz.size:
                    notEnd = False
            print(packetsRetransmitted)
        elif (choice == '4'):
            notEnd = True
            canSend = True
            window = 5
            iterator = 0
            # packetsSent = 0
            neededPacket = 0
            packetsReceived = 0
            packetsRetransmitted = 0
            toResend = []
            sent = []
            kolejka.append(0)
            kolejka.append(0)
            kolejka.append(0)
            kolejka.append(0)
            kolejkaZwrotna.append(0)
            kolejkaZwrotna.append(0)
            kolejkaZwrotna.append(0)
            kolejkaZwrotna.append(0)
            damagedPacketsSent = 0
            Sn = 0              #sequencenumber
            Sb = 0              #sequencebase
            Sm = window-1       #sequencemax
            print(len(kolejka))
            while notEnd:
                if kolejkaZwrotna[len(kolejkaZwrotna) - 1] == 2:
                    Sb = copy.deepcopy(toResend[len(toResend) - 1])
                    Sn = copy.deepcopy(Sb)
                    toResend.pop()
                    print("Otrzymano polecenie retransmiji pakietu " + str(Sb))

                if kolejkaZwrotna[len(kolejkaZwrotna) - 1] == 1:
                    if Sm < obraz.size:
                        Sb = Sb + 1
                        Sm = Sm + 1

                if Sn < Sm and Sn < obraz.size:
                    toSend = copy.deepcopy(pakiety[Sn])
                    toSend.damageData(damageSize)
                    Sn = Sn + 1
                    print("Wyslano pakiet nr " + str(Sn - 1))

                kolejkaZwrotna.pop()

                if toSend != 0:
                    sent.insert(0, toSend)
                    toSend = 0
                    kolejka.insert(0, 1)
                else:
                    kolejka.insert(0, 0)

                if kolejka[len(kolejka) - 1] == 1:
                    recieved = copy.deepcopy(sent[len(sent) - 1])
                    sent.pop()
                    packetNumber = recieved.getIndex()
                    # print("PacketNumber: " + str(packetNumber))
                    # print("NeededPacket: " + str(neededPacket))
                    if not recieved.checkIfDamaged():
                        if packetNumber == neededPacket:
                            if recieved.checkIfDamagedCRC():
                                damagedPacketsSent = damagedPacketsSent + 1
                            neededPacket = neededPacket + 1
                            packetsReceived = packetsReceived + 1
                            zmienna = 0
                            for i, bit in enumerate(recieved.data):
                                zmienna = zmienna + recieved.data[i] * pow(2, Packet.PACKET_SIZE - 1 - i)
                            efektPrawieKoncowy.append(zmienna)
                            kolejkaZwrotna.insert(0, 1)
                            print("Otrzymano pakiet nr " + str(packetNumber))
                            print("Wysylam żądanie pakietu " + str(neededPacket))
                        else:
                            kolejkaZwrotna.insert(0, 0)
                    elif recieved.checkIfDamaged():
                        kolejkaZwrotna.insert(0, 2)
                        packetsRetransmitted = packetsRetransmitted + 1
                        print("Pakiet uszkodzony! Wysyłam żądanie retransmisji pakietu " + str(neededPacket))
                        toResend.insert(0, copy.deepcopy(neededPacket))
                elif kolejka[len(kolejka) - 1] == 0:
                    kolejkaZwrotna.insert(0, 0)
                kolejka.pop()
                iterator = iterator + 1
                print("Upłynęło jednostek czasu " + str(iterator))
                if len(efektPrawieKoncowy) == obraz.size:
                    notEnd = False
            # print(packetsRetransmitted)
        elif (choice == '5'):
            efektKoncowy = np.ndarray((ySize, xSize), np.int32)
            i2 = xSize + 1
            i3 = xSize
            ii = 0
            ii2 = 0
            for i, zmienna2 in enumerate(efektPrawieKoncowy):
                if (i % i2 == i3):
                    i2 = xSize
                    i3 = 0
                    ii = ii + 1
                    ii2 = 0
                efektKoncowy[ii][ii2] = np.int32(zmienna2)
                ii2 = ii2 + 1
            saveImage(efektKoncowy, ("sent" + nazwa))
            print("Obraz zapisano pomyślnie!")
        elif (choice == '6'):
            print("\nRozmiar pliku = " + str(obraz.size))
            print("Wymiary: " + str(xSize) + "x" + str(ySize))
            print("Uzycie pasma transmisji w %: " + str((obraz.size + packetsRetransmitted)/iterator*100))
            print("Uszkodzone pakiety przeslane jako poprawne: " + str(damagedPacketsSent))
            print("Ilosc pakietow wyslana ponownie: " + str(packetsRetransmitted))
            print("Ilosc jednostek czasu ktorych wymagalo przeslanie obrazka dla czasu przesylu 4: " + str(iterator))
            print("Prawdopodobienstwo odwrocenia bitu w %: " + str(damageSize * 100) + "\n\n")
        elif (choice == '0'):
            choice = '0'
        else:
            choice = '1'

print("Ilosc bitow danych w pakiecie: 8")
print("Ilosc bitow checksumy w pakiecie: dobierana dynamicznie")
print("Ilosc bitow indeksu w pakiecie: 20")
print("Szerokosc okna: 5")
print("Odleglosc pomiedzy nadajnikiem a odbiornikiem: 4 skoki")
menu()

