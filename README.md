# SofaCollider

A [SOFA](https://www.sofaconventions.org/mediawiki/index.php/SOFA_(Spatially_Oriented_Format_for_Acoustics))
interface for [the SuperCollider language](https://supercollider.github.io/).

## What is SOFA

SOFA is a data format for binaural audio and head-related transfer functions.
UC Davis's CIPIC lab website has
[a good introduction](https://www.ece.ucdavis.edu/cipic/spatial-sound/) to the
topic

## Installation

### Requirements

In order to install and use the SofaCollider Quark, you of course need to have
the SuperCollider installed, but you'll also need matlab or octave installed,
as well as the SOFA matlab/octave package, and any dependencies it may need.
A full list of dependencies below

- SuperCollider language
- Octave
- Octave NetCDF package (needed for SOFA API)
- SOFA matlab/octave API

### Installation Instructions

- **Octave**: Please refer to the
  [octave website](https://www.gnu.org/software/octave/download) for
  installation instructions

- **Octave NetCDF package**: Once you have octave installed, you can use
  octave's package manager to install the
  [NetCDF package](https://octave.sourceforge.io/netcdf/index.html)

- **SOFA Matlab/Octave API**: This package is not officially registered with
  Octave's package manager so you'll need to manually download the package
  source code into a specific place so that the SofaCollider Quark can see
  where it is (More info on that to come)

## Usage
