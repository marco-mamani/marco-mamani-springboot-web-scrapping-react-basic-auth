import React, { useEffect, useState } from 'react'
import { Navigate } from 'react-router-dom'
import { Container } from 'semantic-ui-react'
import { useAuth } from '../context/AuthContext'
import { webScrapApi } from '../misc/WebScrapApi'
import { handleLogError } from '../misc/Helpers'
import LinkList from './LinkList'

function UserPage2() {
  const Auth = useAuth()
  const user = Auth.getUser()
  const isUser = user.role === 'USER'

  const [links, setLinks] = useState([])
  const [linkTextSearch, setLinkTextSearch] = useState('')
  const [isLinksLoading, setIsLinksLoading] = useState(false)

  useEffect(() => {
    handleGetLinks()
  }, [])

  const handleInputChange = (e, { name, value }) => {
    if (name === 'linkTextSearch') {
      setLinkTextSearch(value)
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

  if (!isUser) {
    return <Navigate to='/' />
  }

  return (
    <Container>
      <LinkList
        isLinksLoading={isLinksLoading}
        linkTextSearch={linkTextSearch}
        links={links}
        handleInputChange={handleInputChange}
        handleSearchLink={handleSearchLink}
      />
    </Container>
  )
}

export default UserPage2