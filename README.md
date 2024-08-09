## Demonstration Video Format

The demonstration video was recorded in a .mp4 file.

## Execution Instructions

### Operating System Compatibility
- Windows 7/8/10/11

### Step 1: Install Oracle JDK
If the exact version of the Oracle JDK has already been installed, disregard this step.
- Download and install version 22.0.1 of Oracle JDK from [this link](https://www.oracle.com/java/technologies/downloads/#jdk22-windows).
   
### Step 2: Install JavaFX SDK
If the exact version of the JavaFX SDK has already been installed, disregard this step.
- Download and install version 22.0.1 of JavaFX SDK from [this link](https://gluonhq.com/products/javafx/).

### Step 3: Set Up Environment Variable
If the Environment Variable has already been set up, disregard this step.
- Add the JDK to PATH by setting the Path variable to the bin folder of the newly installed Java JDK version 22.0.1.
   - Search for ‘Environment Variables’ in the Windows search bar and select “Edit the system environment variables”.
   - In the System Properties window, click on the “Environment Variables” button.
   - Under System Variables, find and select the ‘Path’ variable, then click on “Edit”.
   - Click “New” and add the path to the `bin` folder of your JDK installation.

### Step 4: Run the Application
- Open up Command Prompt from the Windows search bar.
- Use the following command to run the JavaFX JAR application. Replace `<path of JavaFX SDK directory>` with the actual path where the JavaFX SDK was extracted to, and `<path of JAR file>` with the actual path to the downloaded JavaFX JAR file. Ensure to wrap both paths with quotation marks ("").
   - For example, "C:\Program Files\javafx-sdk-22.0.1\lib" and "C:\Users\Jaden\CL_Monday06pm_Team304.jar" respectively.
   ```bash
   java --module-path <path of JavaFX SDK directory>/lib --add-modules javafx.controls,javafx.fxml -jar <path of JAR file>
