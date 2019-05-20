from myLib1 import convertEuro
from myLib1 import convertUSD


def test_euro_to_pln():
    assert convertEuro.euro_to_pln(101) == 430.3, 'incorrect freezing point!'


def test_euro_to_pln():
    assert convertUSD.euro_to_pln(100) == 387.84, 'incorrect freezing point!'
