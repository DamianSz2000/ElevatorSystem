# Project description
`ElevatorSystem` is a simple elevator control system that can be used to control a set of elevators in a building. It is written in Java 19 and used Swing for GUI.
# How to run
1. Clone the repository
2. Open the project in IntelliJ IDEA or other IDE
3. Run the `Main` class
4. Use the GUI to control the elevators
# How to use
When you first run the program you are going to be presented with elevator number input. Here you specify how many elevators are going to be in the building.
Next you are going to be presented with main GUI, here we got few buttons and fields:
- `Pickup floor` and `Target floor` fields are used to specify the floor where you are and the floor where you want to go. Request is submitted by clicking `Add request` button.
- `Generate random requests` button is used to generate given number of random requests.
- `Start simulation` button is used to start the simulation. It will run until all requests are completed.
- `Show requests` button is used to show all requests that are currently in the system.
- `Reset` button is used to reset the system to the initial state and allows you to change the configuration.
# Basic information
- Elevators are numbered from 0 to n-1, where n is the number of elevators in the system.
- Status of each elevator is displayed as `Elevator ID: , Current Floor: , Target Floor: , Status: `, in which:

    - `Elevator ID` is the ID of the elevator
    - `Current Floor` is the floor where the elevator is currently located
    - `Target Floor` is the floor where the elevator is heading
    - `Status` is the status of the elevator, it can be `IDLE`, `MOVING_UP`, `MOVING_DOWN`, `LOADING`(taking people in), `UNLOADING`(letting people out)
