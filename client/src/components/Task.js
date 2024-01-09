import React, { useState } from 'react';
import {
  Card,
  CardContent,
  Typography,
  Button,
  Checkbox,
  Dialog,
  DialogContent,
  DialogActions,
  TextField,
} from '@mui/material';

function Task({ task, onEdit, onDelete }) {
  const [isEditing, setIsEditing] = useState(false);
  const [editedHeadline, setEditedHeadline] = useState(task.headline);
  const [editedNotes, setEditedNotes] = useState(task.notes);

  const handleEditClick = () => {
    setIsEditing(true);
  };

  const handleCancelEdit = () => {
    setIsEditing(false);
  };

  const handleSaveEdit = () => {
    onEdit({ ...task, headline: editedHeadline, notes: editedNotes });
    setIsEditing(false);
  };

  const handleDeleteClick = () => {
    setOpenDeleteDialog(true);
  };

  const handleConfirmDelete = () => {
    onDelete(task.id);
    setOpenDeleteDialog(false);
  };

  return (
    <Card>
      <CardContent>
        <Checkbox checked={task.completed} onChange={() => onEdit({ ...task, completed: !task.completed })} />
        <Typography variant="h5">{isEditing ? editedHeadline : task.headline}</Typography>
        <Typography variant="body2">{isEditing ? editedNotes : task.notes}</Typography>
        {isEditing ? (
          <Dialog open={isEditing} onClose={handleCancelEdit}>
            <DialogContent>
              <TextField
                label="Headline"
                value={editedHeadline}
                onChange={(e) => setEditedHeadline(e.target.value)}
              />
              <TextField
                label="Notes"
                value={editedNotes}
                onChange={(e) => setEditedNotes(e.target.value)}
                multiline
              />
            </DialogContent>
            <DialogActions>
              <Button onClick={handleCancelEdit}>Cancel</Button>
              <Button onClick={handleSaveEdit}>Save</Button>
            </DialogActions>
          </Dialog>
        ) : (
          <>
            <Button onClick={handleEditClick}>Edit</Button>
            <Button onClick={handleDeleteClick}>Delete</Button>
          </>
        )}
      </CardContent>
    </Card>
  );
}

export default Task;
