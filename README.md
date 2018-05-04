# Environment setup 
## Steps
#### Download and install Intellij Idea
1. Go to [Jetbrains.com](https://www.jetbrains.com/idea/download/#section=windows)
2. Choose Community version
3. Download and install the file
#### Download and install JDK
1. Go to [oracle.com](http://www.oracle.com/technetwork/java/javase/downloads/index.html)
2. 'Download' tab should be selected
3. Click on 'Java Platform (JDK) 10'
4. Download 'jdk-10.0.1_windows-x64_bin.exe' file
(be sure what OS and bit you use)
#### Download and install last version of Firefox browser
#### Open Intellij Idea
1. Choose UI theme
2. Skip 'remaining and set defaults'
3. Click on 'Create new project'
4. Project SDK: choose JDK file
5. Give a 'project name' and pay attention to 'project location'

#### Additional settings
1. 'qaauto' - on this parent folder of project right-click -
'Add framework support' - Choose 'Maven'
2. Download [Geckodriver file](https://github.com/mozilla/geckodriver/releases)
copy this file to windows/system 32
3. Download a [Markdown plugin](http://plugins.jetbrains.com/plugin/7793-markdown-support)
for creation of files with .md extension
4. For adding a plugin go to: File - Settings - Plugin - Select a zip file


Example of XPath:
//div[@class='srg']/div[@class='g']

####How to add source code into GitHub repository
**Preconditions:**
1. Register an account on [https://github.com/](https://github.com/)

**Steps:**
1. Log in with your credentials to [https://github.com/](https://github.com/)
1. Click on 'Start a project'
1. Give a repository name here, [screenshot](https://prnt.sc/jdkf87)
(**Note:** this name should be equal to the project name and folder, where your project is put
 (in our case should be the name: 'qaauto-20.04.2018'))
1. Click on 'addignore' drop-down and select 'Java' [screenshot](https://prnt.sc/jdkgvh)
1. Click on 'creat repository'
1. Pay attention that 'gitignore' file should be put in root of created repository (without any folders or sub-folders)
1. In 'gitignore' file write some lines that will exclude some folders and files from Git, which
is not mandatory<br/>
Example:<br/>
> *.iml<br/>
> .idea/<br/>
> target/<br/>

* Click on 'Upload files', [screenshot](https://prnt.sc/jdkk2i)
* You can 'drag'n'drop' project files from project folder 'qaauto-20.04.2018'
**Note:** pay attention that files which you mentioned on 'gitignore' or
other temporary files should be included. After your uploading only
the next files should present as on [screenshot](https://prnt.sc/jdklvm)<br/>
Here the names of these files:<br/>
>src/main/java<br/>
.gitignore<br/>
README.md<br/>
pom.xml<br/>
* After that you can delete your project from local place
and download your project from Git
#### How to donload project from Git to Intellij Idea
* Intellij Idea. Top navigation panel. VCS -> Checkout from version control
-> Git
URL: should be a link from Github, how to get this:
1. GitHub. On repository click on 'Clone or download', copy
the link ([screenshot](https://prnt.sc/jdlved)) and paste this link to Intellij Idea
Directory: local place where project should be put
1. Click on 'Test' button, expected: 'connection successful' message
1. Click on 'Login to GitHub', login via your credentials and after that
click on 'Clone', expected: project from GitHub should be downloaded

####How to commit and push changings made on Intellij Idea to GitHub
1. IntellijIdea. Right click on project parent folder 'qaauto-20.04.2018'
2. Click on Git -> Commit directory
3. Type a 'commit message'
4. Click on 'Commit' drop-down arrow (not a button, but arrow)
5. Click on 'Commit and Push'