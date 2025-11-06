<h3>Welcome to Faux Data (backend)</h3>

<h4>This is a free and open source software application to generate excel and sql files quickly to generate excel spreadsheets and mysql database scripts to provide data to software engineers, data analysts
and other data professionals.</h4>

<b>Open Source</b>
You can:
<ul>
  <li>Use it 100% free</li>
  <li>Make any changes you want to tailor your needs.</li>
  <li>Make money off it.</li>
  <li>Use it to teach.</li>
  <li>And much more</li>
</ul>

<b>Installation Requirements</b>
<ul>
  <li>Minimum Java 17</li>
  <li>Spring Boot</li>
  <li>MySQL</li>
  <li>Docker (Optional)</li>
</ul>

<b>Installation Instructions</b>
<ol>
  <li>Take this file <a href="https://github.com/hiddencodxr/faux-data/blob/master/create_audit_table.sql" target="_blank">Mysql File<a/> and run it in your Mysql DB instance that
  is running.</li>
  <li>CLone this repository into your local workspace.</li>
  <li>Make edits to the <i>'application.properties'</i> file.
    <ol>
      <li>Update the database url in the <i>spring.datasource.url</i> property to match yours.</li>
      <li>Update the spring.datasource.username and spring.datasource.password properties to what your database credentials are. </li>
      <li>Optional: Edit the server.port property to which ever port you would like.</li>
      <li>Update the <i>FileLocations.java</i> class to point to a desired folder location by changing the 'LOCAL_FILE_LOCATION' variable.</li>
    </ol>
  </li>
  <li>Start the FauxDataApplication.java class.</li>
</ol>

<h4>The backend should now be up and running.</h4>

<h4>Now let us set up the UI</h4>
<ol>
  <li>Clone this repository next: <a href="https://github.com/hiddencodxr/faux_data_ui" target="_blank">Faux Data UI</a></li>
</ol>
