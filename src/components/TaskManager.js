import React, { useState, useEffect } from 'react';
import Task from './Task';

function TaskManager() {
  const [tasks, setTasks] = useState([]);

  useEffect(() => {
    // Fetch tasks from a data source (replace with your actual data fetching logic)
    const fetchTasks = async () => {
      const fetchedTasks = await axios.get("http://localhost:8080/"); // Replace with your data fetching implementation
      setTasks(fetchedTasks);
    };
    fetchTasks();
  }, []);

  const onEditTask = (updatedTask) => {
    setTasks((prevTasks) => prevTasks.map((task) => (task.id === updatedTask.id ? updatedTask : task)));
  };

  const onDeleteTask = (taskId) => {
    deleteTaskFromDataSource(taskId);
    setTasks((prevTasks) => prevTasks.filter((task) => task.id !== taskId));
  };

  return (
    <div>
      {
        tasks.map((x, i) => {
          <Task task={x} onEditTask={onEditTask} onDeleteTask={onDeleteTask} />
        })
      }
    </div>
  );
}

export default TaskManager;
