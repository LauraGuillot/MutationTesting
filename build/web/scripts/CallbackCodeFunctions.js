function my_save(id, content) {
    alert("Here is the content of the EditArea '" + id + "' as received by the save callback function:\n" + content);
}

function my_load(id) {
    editAreaLoader.setValue(id, "The content is loaded from the load_callback function into EditArea");
}

function test_setSelectionRange(id) {
    editAreaLoader.setSelectionRange(id, 100, 150);
}

function test_getSelectionRange(id) {
    var sel = editAreaLoader.getSelectionRange(id);
    alert("start: " + sel["start"] + "\nend: " + sel["end"]);
}

function test_setSelectedText(id) {
    text = "[REPLACED SELECTION]";
    editAreaLoader.setSelectedText(id, text);
}

function test_getSelectedText(id) {
    alert(editAreaLoader.getSelectedText(id));
}

function editAreaLoaded(id) {
    if (id == "example_2")
    {
        open_file1();
        open_file2();
    }
}

function toogle_editable(id)
{
    editAreaLoader.execCommand(id, 'set_editable', !editAreaLoader.execCommand(id, 'is_editable'));
}
