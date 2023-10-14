# CSC540 Project

The WolfMedia Streaming Service is a database management system that has a ‘Main Menu’
with the following operations: ‘Insert’, ‘Delete’, ‘Update’, ‘Find’, ‘Payments’, ‘Reports’,
‘Initialize/Reload Database’, ‘Exit()’. A command line prompt is generated asking the user to
select one of the above mentioned options. Each operation further has a SubMenu listing all the
specific operations. Our system has a Main.java class which runs the entire operation and
facilitates switching between the SubMenus.
Reports print out query results to the console, and sometimes console input such as the song id
is required to print out specific results. The console prompts the person running the program for
the information needed for a specific report.
Similarly, the console is interactively used to carry out other operations such as inserting,
updating, or deleting rows in various tables.
The Pays table contains payments for both Record Labels and Podcast Hosts because both
classes are children of “Earner”, so they both have earner id’s. However, Artists are not a child
of “Earner”, because they are paid by the Record Labels instead of being paid directly by the
streaming service. Earners are all paid directly by the streaming service. Payments to Artists are
instead tracked in the PaysArtists table.
The Pays table and PaysArtistsTables only has one row per month-year and id combination.
Making the primary key be this combination helps make reporting easier. The month-year
values are stored as “Time” type variables, but they are formatted in reports to show the
abbreviated month word and year.
The money that the streaming service makes is tracked in the table
StreamingServiceMonthlyRevenue. This table also has a month-year column similar to that of
the Pays and PaysArtists tables.
Each podcast episode has a listening count and an advertisement count. These attribute values
help calculate how much the hosts get paid.
Record labels get paid based on all of the songs that associated artists sing with the calculation
of royalty rate times play count. Then, the record labels pass on 70% of these earnings to the
artists. This part is determined in such a way that artists who sing more songs get paid more.
We also did away with our initial plan from Part 1 to have a Streaming Service entity set table,
because we only need one streaming service entity. This also simplified a lot of operations.
Functional Roles:

Part 1:
- Software Engineer: Anagha (Prime), Sumedh (Backup)
- Database Designer/Administrator: Mohit (Prime), Iris (Backup)
- Application Programmer: Iris (Prime), Mohit (Backup)
- Test Plan Engineer: Sumedh (Prime), Anagha (Backup)
-
Part 2:
- Software Engineer: Anagha (Prime), Mohit (Backup)
- Database Designer/Administrator: Mohit (Prime), Anagha (Backup)
- Application Programmer: Iris (Prime), Sumedh (Backup)
- Test Plan Engineer: Sumedh (Prime), Iris (Backup)
-
Part 3:
- Software Engineer: Anagha (Prime), Iris (Backup)
- Database Designer/Administrator: Mohit (Prime), Sumedh (Backup)
- Application Programmer: Iris (Prime), Anagha (Backup)
- Test Plan Engineer: Sumedh (Prime), Mohit (Backup)

  Sumedh helped test code and helped us to eliminate bugs and increase code robustness.
  Anagha helped generate plans for the code and also helped with implementation. Mohit helped
  with contributions to the database design and implementation. Iris helped program the
  application, including implementing the reports print out functions.
