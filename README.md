# sugoi-lt-time-viewer
## WS
### Client Side
```
SENDER|CMD#ARGUMENT
```
|Command|Name|Descrption|
|-------|----|----------|
|`RLT`|**R**equest **LT** Information|Request sending LT information.|

### Server Side
```
SENDER|EXECUTED_CMD>>CMD#ARGUMENT
```
|Command|Name|Descrption|
|-------|----|----------|
|`?#error_text`|Error|The command the client dispatched could not be run|
|`SLT#json`|**S**ending **LT** Information|Sending LT Information.|

