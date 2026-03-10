import { Box, Button, Typography } from "@mui/material";
import { useContext, useEffect, useState } from "react";
import { AuthContext } from "react-oauth2-code-pkce";
import { useDispatch } from "react-redux";
import { BrowserRouter as Router, Navigate, Route, Routes } from "react-router";
import { setCredentials } from "./store/authSlice";
import ActivityForm from "./components/ActivityForm";
import ActivityList from "./components/ActivityList";
import ActivityDetail from "./components/ActivityDetail";

const ActivitiesPage = () => {
  return (
    <Box sx={{ p: 2, border: '1px dashed grey' }}>
      <ActivityForm onActivitiesAdded={() => window.location.reload()} />
      <ActivityList />
    </Box>
  );
}

function App() {
  const { token, tokenData, logIn, logOut } = useContext(AuthContext);
  const dispatch = useDispatch();
  const [authReady, setAuthReady] = useState(false);
  
  useEffect(() => {
    if (token) {
      dispatch(setCredentials({token, user: tokenData}));
    }
    setAuthReady(true); // ALWAYS set to true after check
  }, [token, tokenData, dispatch]);

  if (!authReady) return <div>Loading...</div>;

  return (
    <Router>
      {!token ? (
        <Box
          sx={{
            height: "100vh",
            display: "flex",
            flexDirection: "column",
            alignItems: "center",
            justifyContent: "center",
            textAlign: "center",
          }}
        >
          <Typography variant="h4" gutterBottom>
            Welcome to the Fitness Tracker App
          </Typography>
          <Typography variant="subtitle1" sx={{ mb: 3 }}>
            Please login to access your activities
          </Typography>
          <Button variant="contained" color="primary" size="large" onClick={() => logIn()}>
            LOGIN
          </Button>
        </Box>
      ) : (
        <Box sx={{ p: 2 }}>
          <Button variant="contained" color="secondary" onClick={logOut} sx={{ mb: 2 }}>
            Logout
          </Button>
          <Routes>
            <Route path="/activities" element={<ActivitiesPage />} />
            <Route path="/activities/:id" element={<ActivityDetail />} />
            <Route path="/" element={<Navigate to="/activities" replace />} />
          </Routes>
        </Box>
      )}
    </Router>
  );
}

export default App;