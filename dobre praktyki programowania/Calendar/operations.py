import ui
import storage
import datetime

CONFIRM_COLOR = ui.GREEN


def add_new_note(list):
    title = ui.title('Wprowadz tytul notatki: ')
    note = ui.note('Wprowadz notatke: ')
    print('Wprowadz dzien')
    day = int(input())
    print('Wprowadz miesiac')
    month = int(input())
    print('Wprowadz rok')
    year = int(input())
    dat = datetime.date(year, month, day)
    while True:
        new_note = (day, month, year, title, note)
        list.append(new_note)
        storage.export_data_to(list, 'notes.txt')
        break


def remove_note(list):
    title = ui.title('Wprowadz tytul notatki: ')
    ui.remove(list, title)
    ui.display_message(ui.GREEN, 'Notatka zostala usunieta')


def count_notes(list):
    count = 0
    for element in list:
        count +=1
    print(count)