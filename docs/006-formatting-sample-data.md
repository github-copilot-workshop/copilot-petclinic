# Formatting Sample Data

Working with sample/test data is a very common task when building applications. 

In the Petclinic application, the `src/main/resources/data/ownsers.csv` contains the list of owners formatted in a csv file.

When looking it you can see that you have a column that could be removed (`default`), and ideally it would be nice to add a email for example with the format `first.last@petclinic.com`.

How can you complete the following tasks:

- Reformat the csv file, where you remove the default column and add email
- Create a JSON file, that will be another file for our test environment


<details>
<summary>Possible Flow</summary>

1. Open the `src/main/resources/data/ownsers.csv` file
2. Select the content 
3. Use the Copilot Inline Chat (`Right click > Copilot`), and ask the following:
  
   `In the editor, remove the first column, and add a new one called email based on first.last@petclinic.com`
4. Save the file.


You can now for example copy the file and use the same approach to reformat to JSON or use the Chat using #file command.

- `using #file:ownsers.csv , propose the same data as JSON array (do not provide the code to do it but the formatted data that I can add in a new file)`

</details>