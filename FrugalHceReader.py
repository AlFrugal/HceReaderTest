import os
from smartcard.CardConnection import CardConnection
from smartcard.CardRequest import CardRequest
from smartcard.CardType import AnyCardType
from smartcard.System import readers
from smartcard.util import HexListToBinString, BinStringToHexList, toBytes
from smartcard.util import toHexString, toBytes, PACK


# define the APDUs used in this script
SELECT = [0x00, 0xA4, 0x04, 0x00]
AIDFRUGAL = [0xF0, 0x46, 0x52, 0x55, 0x47, 0x41, 0x4C] 
sizeaid = [0x07]
apduselect= SELECT+ sizeaid + AIDFRUGAL
cardtype = AnyCardType()
cardrequest = CardRequest(timeout =None, cardType=cardtype)

print"Frugal HCE Reader"
print"Posez votre SmartPhone NFC HCE sur le lecteur"

while (True):

    try:
        cardservice = cardrequest.waitforcard()
        cardservice.connection.connect()
        print "ENVOI DU SELECT AID"
        response, sw1, sw2 = cardservice.connection.transmit( apduselect)
        res = toHexString(response) + " " +  toHexString([sw1,sw2])
        print 'response :  '+ res
        print "\n"
    except KeyboardInterrupt: 
        exit()
    except Exception, e: 
        print ""
        

