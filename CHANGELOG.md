# [](https://github.com/waiyaki/simple-math/compare/v0.0.5...v) (2019-04-12)



## [0.0.5](https://github.com/waiyaki/simple-math/compare/v0.0.4...v0.0.5) (2019-04-02)


### Features

* **math:** Better bounded random int generation ([d229fb4](https://github.com/waiyaki/simple-math/commit/d229fb4))
* **math:** provide the answer if can't get it after all retries ([3d28bd8](https://github.com/waiyaki/simple-math/commit/3d28bd8))



## [0.0.4](https://github.com/waiyaki/simple-math/compare/v0.0.3...v0.0.4) (2019-04-01)


### Bug Fixes

* **math:** Disable subtraction and division pending figuring out better limits ([e78b17c](https://github.com/waiyaki/simple-math/commit/e78b17c))
* make gen-random-table generate as many rows as defined by user level ([db06ac6](https://github.com/waiyaki/simple-math/commit/db06ac6))


### Features

* **math:** Generate and attempt a table of math ops ([24306fa](https://github.com/waiyaki/simple-math/commit/24306fa))
* **math:** Positive feedback when user gets op right ([8e77332](https://github.com/waiyaki/simple-math/commit/8e77332))



## [0.0.3](https://github.com/waiyaki/simple-math/compare/v0.0.2...v0.0.3) (2019-03-29)


### Code Refactoring

* **operations:** change how attempt takes options ([2a937ca](https://github.com/waiyaki/simple-math/commit/2a937ca))


### Features

* **attempt:** make attempt optionally fail silently ([eb1ba49](https://github.com/waiyaki/simple-math/commit/eb1ba49))
* **limits:** Add limits ([65f59cd](https://github.com/waiyaki/simple-math/commit/65f59cd))
* **math:** Attempt a single math op ([d74f430](https://github.com/waiyaki/simple-math/commit/d74f430))
* **math:** Generate numbers to use in math ops ([2248e3f](https://github.com/waiyaki/simple-math/commit/2248e3f))
* **prompt:** make prompt optionally accept or reject empty input ([0afe287](https://github.com/waiyaki/simple-math/commit/0afe287))


### BREAKING CHANGES

* **operations:** `attempt` now takes options as a config map



## [0.0.2](https://github.com/waiyaki/simple-math/compare/v0.0.1...v0.0.2) (2019-03-26)


### Features

* **operations:** prompt for and validation operation preference ([d4be92b](https://github.com/waiyaki/simple-math/commit/d4be92b))
* **preferences:** make obtaining and validating preferences reusable ([edff448](https://github.com/waiyaki/simple-math/commit/edff448))
* **preferences:** make preference validation errors more generic ([1f058c9](https://github.com/waiyaki/simple-math/commit/1f058c9))



## [0.0.1](https://github.com/waiyaki/simple-math/compare/175d9ec...v0.0.1) (2019-03-26)


### Bug Fixes

* **levels:** Fix prefix printing when showing levels ([3d28e80](https://github.com/waiyaki/simple-math/commit/3d28e80))
* **prompt:** Fix bug when promting without a provided parser ([0064eef](https://github.com/waiyaki/simple-math/commit/0064eef))


### Features

* **levels:** Add ability to prompt for level preference, with retries ([bdbff47](https://github.com/waiyaki/simple-math/commit/bdbff47))
* **levels:** Show level preferences and accept level input ([175d9ec](https://github.com/waiyaki/simple-math/commit/175d9ec))



