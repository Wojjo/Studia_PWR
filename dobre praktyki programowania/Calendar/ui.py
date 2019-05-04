
def title(message):
    while True:
        title = input(message)
        if len(title) > 41:
            error_message = "Tytul notatki nie moze byc dluzszy niz 40 znakow"
            display_error_message(error_message)
        else:
            return title.title()


def note(message):
    while True:
        meeting_note = input(message)
        return meeting_note


#  displaying functions
DAY = 0
MONTH = 1
YEAR = 2
TITLE = 3
NOTE = 4


def display_all(list):
    print('Wszystkie notatki:')
    for element in list:
        print(f'{int(element[DAY])}.{int(element[MONTH])}.{int(element[YEAR])} Tytul: {element[TITLE]} Notatka: {element[NOTE]}')
    if not element:
        print('(empty)')


def display_chosen(list):
    print('Wprowadz miesiac')
    month = int(input())
    for element in list:
        if int(element[MONTH]) == month:
            print(f'{int(element[DAY])}.{int(element[MONTH])}.{int(element[YEAR])} Tytul: {element[TITLE]} Notatka: {element[NOTE]}')

def remove(list, title):
    print(title)
    for element in list:
        if((element[TITLE]) == title):
            list.remove(element)
            break
    else:
        display_error_message('Nie znaleziono notatki o podanym tytule')




def display_menu(menu_commands):
    print('Menu:')
    for option in menu_commands:
        print(f'({option[0]}) {option}')  # initial letter in brackets and option name


def display_message(color, message):
    print(display_colored_text(color, message))


def display_error_message(message):
    print(display_colored_text(ORANGE, (f'ERROR: {message}')))


#  coloring function
BLUE = '34m'
CYAN = '96m'
GREEN = '92m'
ORANGE = '33m'
RED = '91m'
YELLOW = '93m'


def display_colored_text(color, message):
    colored_text = (f"\033[{color}{message}\033[00m")
    return colored_text
