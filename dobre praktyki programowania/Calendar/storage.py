import ui
CONFIRM_COLOR = ui.GREEN


def import_data_from(source_file):
    list_of_notes_str = []
    with open(source_file, 'r') as datafile:
        for line in datafile.readlines():
            list_of_notes_str.append(line.strip().split(','))
    return list_of_notes_str


def convert_to_tuples_of_ints(strings_list):
    list_of_notes_int = []
    for note in strings_list:
        note_parameters_list = []
        for element in note:
            try:
                note_parameters_list.append(int(element))
            except ValueError:
                note_parameters_list.append(element)
        list_of_notes_int.append(tuple(note_parameters_list))
    return list_of_notes_int


def load_notes(source_file='notes.txt'):
    return convert_to_tuples_of_ints(import_data_from(source_file))


def convert_to_list_of_strigs_from(mixed_list):
    list_of_notes_str = []
    for note in mixed_list:
        note_parameters_list = []
        for element in note:
            note_parameters_list.append(str(element))
        list_of_notes_str.append(note_parameters_list)
    return list_of_notes_str


def export_data_to(data_source, dest_file='notes.txt'):
    list_of_notes_str = convert_to_list_of_strigs_from(data_source)
    with open(dest_file, 'w') as destfile:
        for note in list_of_notes_str:
            destfile.write(','.join(note) + '\n')
            print(','.join(note))
        ui.display_message(CONFIRM_COLOR, 'Meeting added')
