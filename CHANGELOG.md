# [](/compare/v0.0.2...v) (2019-03-29)


### Code Refactoring

* **operations:** change how attempt takes options 2a937ca


### Features

* **attempt:** make attempt optionally fail silently eb1ba49
* **limits:** Add limits 65f59cd
* **math:** Attempt a single math op d74f430
* **math:** Generate numbers to use in math ops 2248e3f
* **prompt:** make prompt optionally accept or reject empty input 0afe287


### BREAKING CHANGES

* **operations:** `attempt` now takes options as a config map



# [0.0.2](/compare/v0.0.1...v0.0.2) (2019-03-26)


### Features

* **operations:** prompt for and validation operation preference d4be92b
* **preferences:** make obtaining and validating preferences reusable edff448
* **preferences:** make preference validation errors more generic 1f058c9



## [0.0.1](/compare/175d9ec...v0.0.1) (2019-03-26)


### Bug Fixes

* **levels:** Fix prefix printing when showing levels 3d28e80
* **prompt:** Fix bug when promting without a provided parser 0064eef


### Features

* **levels:** Add ability to prompt for level preference, with retries bdbff47
* **levels:** Show level preferences and accept level input 175d9ec
