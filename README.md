CreatePhoneCards
================

The reason for writing this program is to make an easy way to download all current brothers' and/or pledges' names, phone numbers, and emails to a smart phone so that you don't have to do it manually, which can be tedious. The current phone card system of printing them out then laminating them then cutting them then handing them out to people at chapter is annoying and not worth the effort or money. This way I don't have to do that anymore.

Running CreatePhoneCards.jar will create three .vcf files, one with brothers info, one with pledges info, and one with both, taken from the Brothers and Pledges Info files. The .jar file works with the current secretary roster spreadsheet, and if you're not the current secretary, running this file really is rather pointless except just for kicks or to learn how it works. The below instructions are for the current and next secretaries, but if you would like to run it you can just run the .jar file as is and it'll overwrite the current .vcf files with the same temporary data already in the respective .tsv files.

Follow these instructions to easily create the phone cards and not have to do all that laminating and cutting stuff that makes you feel like a fifth grader in arts and crafts. That's the whole reason I wrote this program.

1. Open the current Secretary worksheet. This program is based off the Fall 2013 Secretary Worksheet, but it should be the same for future semesters.
2. Click on the Active Roster tab. Click File > Download as > Plain text
3. Rename this file "Info Brothers.tsv".
4. Click on the Pledge Roster tab. Click File > Download as > Plain text
5. Rename this file "Info Pledges.tsv".
6. Place both files in same folder as the .jar file, preferably in a newly named folder following convention like "SPRING 2014".
7. Run "CreatePhoneCards.jar".
8. Open the newly created files in a text editor like Notepad or TextEdit to see if it worked. If they're not empty, it probably worked correctly.
9. To fully test, email the files to yourself and see if you can add the contacts to your phone. They should now come up in searches and when drafting emails, if you use the initials emailing convention, then the correct brother will show up as a selectable recipient.

*If it didn't work, check to see if the .tsv files are formatted the same as 
the "Info Brothers Test.tsv" or "Info Pledges Test.tsv" files*