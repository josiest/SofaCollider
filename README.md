# SofaCollider

A [SOFA](https://www.sofaconventions.org/mediawiki/index.php/SOFA_(Spatially_Oriented_Format_for_Acoustics))
interface for [the SuperCollider language](https://supercollider.github.io/).

## What is SOFA

SOFA is a data format for binaural audio and head-related transfer functions.
UC Davis's CIPIC lab website has
[a good introduction](https://www.ece.ucdavis.edu/cipic/spatial-sound/) to the
topic

## Usage

In order to use the SOFA interface, you'll first needs some HRTFs. The
SofaCollider Quark aims to make this very easy by providing a simple interface
to pre-existing databases of HRTFs.

```
~subjectID = 3;
CIPIC.downloadSubject(~subjectID); // Download the CIPIC subject_003 hrtf data

// create a new Simple Free-Field HRIR object from the downloaded data
~hrtfPath = CIPIC.subjectLocalPath(~subjectID);
~hrtf = SimpleFreeFieldHRIR.newFromFile(~hrtfPath);
```

Once you've created an `hrtf` object, you can query various metadata about the
object. For example:

```
postf("hrtf.sofaConvention: %\n", ~hrtf.sofaConvention);
postf("hrtf.listenerPosition.at(0): %\n", ~hrtf.listenerPosition.at(0));
```

## Installing

If you're familiar with installing quarks, this should be relatively straightforward.
If you have all the prerequisities isntalled, just run the following function on your
SuperCollider IDE:

```
Quarks.install('https://github.com/josiest/SofaCollider')
```

If you've never installed a quark before, you'll likely need to install
[git](https://git-scm.com/). You may also find it helpful to review the
[SuperCollider instruction pages on installing quarks](https://github.com/supercollider-quarks/quarks#installing).

### Requirements

In order to install and use the SofaCollider Quark, you of course need to have
SuperCollider installed, but you'll also need matlab or octave installed,
as well as the SOFA matlab/octave package, and any dependencies it may need.
A full list of dependencies below

- **SuperCollider:** Please refer to the
  [SuperCollider website](https://supercollider.github.io/downloads) for
  installation instructions.

- **Octave**: Please refer to the
  [octave website](https://www.gnu.org/software/octave/download) for
  installation instructions.

  **Specifying a custom command path (NOT RECOMMENDED)**:

  If you don't have octave installed in the normal place it would be installed,
  you'll likely need to specify a custom commmand to run when using octave.
  For example, if your octave command is in `~/Downloads/octave` you can write

  ```
  ~octaveCmd = "~/Downloads/octave".standardizePath;
  SofaColliderConfig.octaveCmd_(~octaveCmd);
  ```

  **NOTE**: This must be done _after_ the `SofaCollider` quark has been
  installed.

- **Octave NetCDF package**: Once you have octave installed, you can use
  octave's package manager to install the
  [NetCDF package](https://octave.sourceforge.io/netcdf/index.html).

  You will likely also need to install netcdf for your system in order for this
  package to work properly. On debian-based linux systems you can acheive this
  with the command `sudo apt install libnetcdf-dev`.

- **SOFA Matlab/Octave API**: This package is not officially registered with
  Octave's package manager so you'll need to manually download the package
  source code into a specific place so that the SofaCollider Quark can see
  where it is. There are two ways to do this:

  **Option 1: Tell SofaCollider where to find the API**

  While it may be cleaner to install the API in a way that matches how official
  packages are installed, this has some amount of a learning curve - and there
  might be other reasons you might want it installed in a specific place. In
  order to accomodate for either of these things, SofaCollider provides an
  interface to specify where the SOFA Matlab/Octave API repository exists.

  For example, if the API is in `~/Downloads/API_MO` you could write

  ```
  ~path = "~/Downloads/API_MO".standardizePath;
  SofaColliderConfig.sofaOctaveRepo_(~path);
  ```

  **NOTE:** you must have SofaCollider installed first for this method to work.
  This is possible because the the SOFA M/O API requirement is only a
  requirement for using the SOFA interface - this method doesn't use that
  functionality of the quark.

  **Option 2: Install API to default directory**

  Navigate to your user application support directory - you can find this
  from SuperCollider from the output of

  `Platform.userAppSupportDir`

  This will give the SuperCollider support directory, so you'll want to navigate
  to the parent directory. On unix systems it might look something like
  `~/.local/share`

  If it doesn't already exist, make the directory system `octave/packages` and
  navigate inside it and run the following command

  `git clone https://github.com/sofacoustics/API_MO.git sofa`
