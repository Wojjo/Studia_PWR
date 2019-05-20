from myLib2 import convertMiles
from myLib2 import convertSeaMile


def test_mile_to_km():
    assert convertMiles.mile_to_km(101) == 162.509, 'incorrect!'


def test_sea_mile_to_km():
    assert convertSeaMile.sea_mile_to_km(101) == 187.052, 'incorrect!'
