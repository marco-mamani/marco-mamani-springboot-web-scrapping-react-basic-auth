import React, { useEffect, useState } from 'react'
import { Navigate } from 'react-router-dom'
import { Container } from 'semantic-ui-react'
import { useAuth } from '../context/AuthContext'
import { webScrapApi } from '../misc/WebScrapApi'
import AdminTab from './AdminTab'
import { handleLogError } from '../misc/Helpers'

function AdminPage() {
  const Auth = useAuth()
  const user = Auth.getUser()
  const isAdmin = user.role === 'ADMIN'

  const [users, setUsers] = useState([])
  const [userUsernameSearch, setUserUsernameSearch] = useState('')
  const [isUsersLoading, setIsUsersLoading] = useState(false)

  const [links, setLinks] = useState([])
  const [linkUrl, setLinkUrl] = useState('')
  const [linkDescription, setLinkDescription] = useState('')
  const [linkTextSearch, setLinkTextSearch] = useState('')
  const [isLinksLoading, setIsLinksLoading] = useState(false)

  useEffect(() => {
    handleGetUsers()
    handleGetLinks()
  }, [])

  const handleInputChange = (e, { name, value }) => {
    if (name === 'userUsernameSearch') {
      setUserUsernameSearch(value)
    } else if (name === 'linkUrl') {
      setLinkUrl(value)
    } else if (name === 'linkDescription') {
      setLinkDescription(value)
    } else if (name === 'linkTextSearch') {
      setLinkTextSearch(value)
    }
  }

  const handleGetUsers = async () => {
    try {
      setIsUsersLoading(true)
      const response = await webScrapApi.getUsers(user)
      const users = response.data
      setUsers(users)
    } catch (error) {
      handleLogError(error)
    } finally {
      setIsUsersLoading(false)
    }
  }

  const handleDeleteUser = async (username) => {
    try {
      await webScrapApi.deleteUser(user, username)
      await handleGetUsers()
    } catch (error) {
      handleLogError(error)
    }
  }

  const handleSearchUser = async () => {
    try {
      const response = await webScrapApi.getUsers(user, userUsernameSearch)
      const data = response.data
      const users = data instanceof Array ? data : [data]
      setUsers(users)
    } catch (error) {
      handleLogError(error)
      setUsers([])
    }
  }

  const handleGetLinks = async () => {
    try {
      setIsLinksLoading(true)
      const response = await webScrapApi.getLinksByUser(user)
      setLinks(response.data)
    } catch (error) {
      handleLogError(error)
    } finally {
      setIsLinksLoading(false)
    }
  }

  const handleDeleteLink = async (id) => {
    try {
      await webScrapApi.deleteLink(user, id)
      await handleGetLinks()
    } catch (error) {
      handleLogError(error)
    }
  }

  const handleAddLink = async () => {
    try {
      const link = { url: linkUrl.trim(), description: linkDescription.trim() }
      if (!(link.url && link.description)) {
        return
      }
      await webScrapApi.addLink(user, link)
      clearLinkForm()
      await handleGetLinks()
    } catch (error) {
      handleLogError(error)
    }
  }

  const handleSearchLink = async () => {
    try {
      const response = await webScrapApi.getLinks(user, linkTextSearch)
      const links = response.data
      setLinks(links)
    } catch (error) {
      handleLogError(error)
      setLinks([])
    }
  }

  const clearLinkForm = () => {
    setLinkUrl('')
    setLinkDescription('')
  }

  if (!isAdmin) {
    return <Navigate to='/' />
  }

  return (
    <Container>
      <AdminTab
        isUsersLoading={isUsersLoading}
        users={users}
        userUsernameSearch={userUsernameSearch}
        handleDeleteUser={handleDeleteUser}
        handleSearchUser={handleSearchUser}
        isLinksLoading={isLinksLoading}
        links={links}
        linkUrl={linkUrl}
        linkDescription={linkDescription}
        linkTextSearch={linkTextSearch}
        handleAddLink={handleAddLink}
        handleDeleteLink={handleDeleteLink}
        handleSearchLink={handleSearchLink}
        handleInputChange={handleInputChange}
      />
    </Container>
  )
}

export default AdminPage