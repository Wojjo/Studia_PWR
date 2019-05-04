import sys
import operations
import storage
import ui


CONFIRM_COLOR = ui.GREEN


def choose_options(list):
    user_choice = input('Your choice: ')
    if user_choice == 'D':
        operations.add_new_note(list)
    elif user_choice == 'W':
        ui.display_all(sorted(list))
    elif user_choice == 'P':
        ui.display_chosen(list)
    elif user_choice == 'U':
        operations.remove_note(list)
    elif user_choice == 'I':
        operations.count_notes(list)
    elif user_choice == 'Q':
        sys.exit()
    else:
        ui.display_message(ui.ORANGE, "Wybierz dostepna opcje")


def display_menu():
    menu_commands = ['Dodaj nowa notatke',
                     'Wyswietl wszystkie notatki',
                     'Pokaz wybrane notatki',
                     'Usun notatke',
                     'Ilosc notatek',
                     'QUIT']
    ui.display_menu(menu_commands)


def main():
    notes = storage.load_notes('notes.txt')
    while True:
        display_menu()
        choose_options(notes)


if __name__ == '__main__':
    main()
