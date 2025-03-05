import { lazy, Suspense } from "react";
import { BrowserRouter, Routes, Route } from "react-router-dom";

const Dashboard = lazy(() => import("./pages/Dashboard"));
function App() {
   return <BrowserRouter>
   <Routes>
     <Route
       path="/shorten-url"
       element={
         <Suspense fallback={"loading..."}>
           <Dashboard />
         </Suspense>
       }
     />
   </Routes>
 </BrowserRouter>
}

export default App
